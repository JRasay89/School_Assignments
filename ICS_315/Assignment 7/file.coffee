###
Implements reading and writing of data to local files using HTML5.
Currently only text files are supported.
Provides limited support of mime types.
Note: Reading is asynchronous, i.e. clients need to provide a callback that consumes data once it's read(y)
Useable only if browser supports HTML5 File APIs, provides for testing such support.
Assumes that jQuery is/will be loaded
(Could be a jQuery plugin)
(Could be a promise/deferred)
(Could be extended to support writing and reading files by dragging them from and to an area on a page)
Copyright 2014 Jan Stelovsky, MIT license (use as you wish, don't complain:)
###
class File
  
  # common mime types available right away, a full suite is loaded asynchronously
  @mimeTypes = 
    txt:    'text/plain'
    html:   'text/html'
    css:    'text/css'
    json:   'application/json'
    js:     'application/javascript'
    xml:    'application/xml'
    dtd:    'application/xml-dtd'
    png:    'image/png'
    jpeg:   'image/jpeg'
    jpg:    'image/jpeg'
    coffee: 'text/coffee'
  # default mime type, will need to changed once binary files are supported
  @defaultType = 'text/plain' # 'application/octet-stream'
  # stores needed DOM page elements so that we don't ask jQuery all the time
  @dom = {}
  
  # Once DOM is ready initializes the DOM and initiates loading full suite of mime types
  $ ->
    # establish API support
    File.dom.body = $ 'body'
    File.supported = []
    File.unsupported = []
    for api in ['File', 'FileReader', 'FileList', 'Blob', 'URL']
      if window[api]? then File.supported.push api else File.unsupported.push api
    # Extracts mime types, associates them with extensions
    onMimeTypes = (mimeTypes) ->
      File.mimeTypes = {}
      for mimeType in mimeTypes
        File.mimeTypes[extension.replace /./, ''] = type for extension, type of mimeType
    onError = -> console.log "GET Error:#{JSON.stringify arguments}"
    # initiate loading full suite of mime types
    $.when($.getJSON 'assets/mime_types.json').done(onMimeTypes).fail(onError)
    File
    
  # Returns message telling which of the various File API are supported by the browser
  @support = ->
    # Makes human-readable text from JSON's stringified array
    list = (names) -> (JSON.stringify names).replace(/\[(.*)\]/, '$1').replace(/"/g, "'").replace(/,/g, ", ").replace /(.*),/, '$1 and'
    # return pretty message
    if File.unsupported.length is 0
      "Your browser supports all the File-related APIs, i.e.:\n  #{list File.supported}"
    else if File.supported.length is 0
      "Your browser doesn't support any File-related APIs, i.e.:\n  #{list File.unsupported}"
    else
      "Your browser supports the following APIs:\n  #{list File.supported}\nbut doesn't support:\n  #{list File.unsupported}"
  
  # Returns true if the File API(s) needed to read files are supported by the browser, false otherwise
  @canRead = -> FileReader?
  
  # Returns true if the File API(s) needed to write files are supported by the browser, false otherwise
  @canWrite = -> URL? and Blob?
    
  # Returns mime type based on the extension of 'fileName'
  @type = (fileName) ->
    # extract extension (w/out the dot)
    extension = fileName.match /\.([0-9a-z]+)(?:[\?#]|$)/i
    # return default type if there is no extension
    return File.defaultType unless extension? or extension is '' or extension.length < 1
    # return corresponding mime type; if there is none return default one
    extension = extension[1]
    if File.mimeTypes[extension]? then File.mimeTypes[extension] else File.defaultType
  
  # Reads a file that the user selects from standard file open dialog
  # Once file contents is available, 'onRead(contents)' will be called
  # 'onFile(file)' will be called once the files metadata is available (reading data may still fail!)
  #   metadata is file.name, file.size (in bytes), file.type (mime type)
  # 'onError(message) is called either if the browser doesn't support reading files
  #   or if metadats doesn't become available
  # To do: handle user cancelling the file open dialog
  @read = (onRead, onError, onFile) ->
    # check whether FileReader API is available
    if FileReader?
      # as reading files is only supported through an <input type="file"> DOM element,
      # insert a dummy hidden <input> to placate the W3C standards (unless we haven't done it yet)
      File.dom.input = $('<input>').attr({type:'file', href:'#'}).hide().appendTo File.dom.body unless File.dom.input
      # read file once the <input> is clicked
      File.dom.input.on 'change', ->
        # Retrieve the first file from the FileList object
        file = $(@).get(0).files[0]
        if file?
          # metadata is available, call 'onFile(file)'
          onFile(file) if onFile?
          reader = new FileReader()
          # Calls 'onRead(contets)' once it's available
          reader.onloadend = (event) => onRead(event.target.result)
          # initiate  reading
          reader.readAsText file
        else # probably user cancelled; we may want to call onRead(null)/
          onError "Couldn't get metadata of from element '#{id}" if onError?
      # programatically trigger clicking the dummy <input>
      File.dom.input.trigger 'click'
    else
      onError "Your browser doesn't support reading files, sorry..." if onError?
      
  # Writes 'data' to a file that the user selects from standard file save dialog
  # To do: onWritten isn't supported, yet
  @write = (fileName, data, onError, onWritten) ->
    # check whether URL and Blob APIs are available
    if URL? and Blob?
      # as writing files is only supported through an <a download="some-file-name"> DOM element,
      # insert such a dummy hidden <a> to placate the W3C standards (unless we haven't done it yet)
      File.dom.a = $('<a>').hide().appendTo File.dom.body unless File.dom.a
      # find the right mime type
      type = File.type fileName
      # fill an URL object with the data
      # note: large (binary) data will have to be filled piecemeal into the array for Blob 
      href = URL.createObjectURL(new Blob([data], {type: type}))
      # fill the dummy <a> with the weird needed attributes
      options =
        download: fileName
        href: href
        'data-downloadurl': "#{type}:#{fileName}:#{href}"
      File.dom.a.attr options
      # programatically trigger clicking the dummy <a>
      # note: can't be done with jQuery's trigger('click')!
      File.dom.a[0].click()
      # After some delay, release the storage the data needed (delay needed to work properly)
      cleanUp = -> URL.revokeObjectURL href
      setTimeout cleanUp, 1500
    else
      onError("Your browser doesn't support writing files, sorry...") if onError?

  # make this module available globally in the JS namespace 
  window.URL = window.webkitURL or window.URL
  window.JS = {} if not window.JS
  window.JS.File = File
  
###
coffee -cwo /Users/jan/parwinr/parvina.co.nf/parvina.co.nf/javascripts/ /Users/jan/parwinr/parvina.co.nf/parvina.co.nf/coffeescripts/

###

###
    holder = $ '#holder'
    state = $ '#status' 
    if window.FileReader?
      state.className = 'success'
      state.innerHTML = 'File API & FileReader available'
    else
      state.className = 'fail'
 
    holder.ondragover = ->
      @className = 'hover'
      false
    holder.ondragend = ->
      @className = ''
      false
    holder.ondrop = (event) ->
      @className = ''
      event.preventDefault()
      file = event.dataTransfer.files[0]
      reader = new FileReader()
      reader.onload = (event) ->
        console.log event.target
        holder.style.background = "url(#{event.target.result}) no-repeat center"
      console.log file
      reader.readAsDataURL file
      false;
###