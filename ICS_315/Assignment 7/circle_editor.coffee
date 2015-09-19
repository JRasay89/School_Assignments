###
Implements a primitive Web App editor for a circle.
Showcases using jQueryUI's menus and dialogs.
The File > Save... menu command writes the current circle attributes to a file.
The File > Open... menu command reads a previously stored circle attributes from a file and updates the circle accordingly.
The Edit > Change menu command displays a dialog where the user can define the circle's attributes:
  its center, radius, and colors inside and on the perimeter
Copyright 2014 Jan Stelovsky, MIT license (use as you wish, don't complain:)
###  
$ ->
  # Prepare easy access to DOM elements by theis ids
  ids = [
    'menubar', 'file_menu', 'open', 'save', 'edit_menu', 'change', 'create', 'copy', 'cut', 'paste', 
    'canvas', 'dialog','dialog2', 'x', 'y', 'radius', 'color', 'stroke'
  ]
  #Variables
  dom = {}
  circleArray = []
  jsonCopy = 0
  selectedCircle = 0 
  dom[id] = $("##{id}") for id in ids
  # establish menus in a horizontal menubar
  options =
    # Once a (sub)menu is activated, adjust its position 
    focus: (event, ui) ->
      isTopMenu = dom.menubar[0] is ui.item.parent()[0]
      position =
        my: 'left top'
        at: if isTopMenu then 'left bottom' else 'right top'
      dom.menubar.menu 'option', 'position', position
  dom.menubar.menu options
  # make menubar as wide as necessry to accomodate top menus
  topMenus = $('#menubar > li')
  dom.menubar.width topMenus.length * topMenus.width()
  # show icons; see circle_editor.css for explanation why this can't be done in the page HTML
  $('#menubar span').addClass 'ui-icon'
  # if reading files isn't supported disable 'Open' menu item and update its the tooltip accordingly
  unless JS.File.canRead()
    dom.open.attr({title:"Your browser can't read files, sorry..."}).parent().addClass 'ui-state-disabled'
  # if reading files isn't supported disable 'Save' menu item and update its the tooltip accordingly
  unless JS.File.canWrite()
    dom.save.attr({title:"Your browser can't write files, sorry..."}).parent().addClass 'ui-state-disabled'
  # create default circle
  circle = new JS.Circle dom.canvas
  circleArray.push circle #store the new circle object in the circle array
  circleArray[0].draw() #draw the initial circle object
  # prepare dialog
  dialogOptions =
    modal: true
    autoOpen: false
  dom.dialog.dialog dialogOptions
  dom.dialog2.dialog dialogOptions
  # menu action 'Open': read json of previously saved circle from a file and update the circle accordingly
  dom.open.click ->
    onRead = (json) ->
      circleArray[0].set JSON.parse json
    JS.File.read onRead
    dom.menubar.blur()  # hide the menu
  # menu action 'Save As': write json of the circle to a 'circle.json' file
  dom.save.click ->
    # get properties of the current circle in JSON format
    arrayLength = circleArray.length
    i = 0
    while i < arrayLength
      json = JSON.stringify circleArray[0].get()
      # write the JSON to 'circle.json' file (the destination will be the downloads folder)
      JS.File.write 'circle.json', json
      i++
    dom.menubar.blur()  # hide the menu
    
  # menu action 'Change': display dialog with properties of the circle 
  # and update the circle with the user-entered values if the user approves
  dom.change.click ->
    dom.menubar.blur()  # hide the menu
    # fill the fields in the dialog with the properties of the circle
    dom[key].val value for key, value of circleArray[selectedCircle].get()
    buttons =
      # if the user clicks 'Change' in the dialog update the properties of the circle
      Change: ->
        # retrieve the user-entered values from the dialog 
        specs = {}
        specs[key] = dom[key].val() for key in ['x', 'y', 'radius', 'color', 'stroke']
        # set the circle's properties to these values
        circleArray[selectedCircle].set specs
        i = 0
        while i < circleArray.length         
          if i is selectedCircle
            #Do Nothing
          else
            circleArray[i].reDraw()
          i++
        # close the dialog
        dom.dialog.dialog 'close'
      # if the user cancels just close the dialog
      Cancel: -> dom.dialog.dialog 'close'
    # update the dialog and open it 
    dom.dialog.dialog 'option', 'buttons', buttons
    dom.dialog.dialog 'open'
    
  #Resets and create a new circle
  dom.create.click ->
    dom.menubar.blur()  # hide the menu
    # fill the fields in the dialog with the properties of the circle
    dom[key].val value for key, value of circle.get()
    buttons =
      # if the user clicks 'Change' in the dialog update the properties of the circle
      Create: ->
        # retrieve the user-entered values from the dialog 
        specs = {}
        specs[key] = dom[key].val() for key in ['x', 'y', 'radius', 'color', 'stroke']
        # set the circle's properties to these values
        circleArray.push new JS.Circle dom.canvas #store the new circle object in the circle array
        circle.clear()
        selectedCircle = circleArray.length - 1
        i = 0
        while i < circleArray.length         
          if i is selectedCircle
            circleArray[circleArray.length - 1].paste specs
          else
            circleArray[i].reDraw()
          i++
        alert "Selected newly created Circle # #{selectedCircle}"
        # close the dialog
        dom.dialog.dialog 'close'
      # if the user cancels just close the dialog
      Cancel: -> dom.dialog.dialog 'close'
    # update the dialog and open it
    dom.dialog.dialog 'option', 'buttons', buttons
    dom.dialog.dialog 'open' 
  #copy the current cirlce
  dom.copy.click ->
    jsonCopy = JSON.stringify circleArray[selectedCircle].get() #get the specs of the selected circle
  #copy and delete current circle
  dom.cut.click ->
    jsonCopy = JSON.stringify circleArray[selectedCircle].get()
    circleArray.splice(selectedCircle, 1)
    circle.clear()
    arrayLength = circleArray.length
    i = 0
    while i < arrayLength         
      circleArray[i].reDraw()
      i++
  #paste the copied cirlce 
  dom.paste.click ->
    circleArray.push new JS.Circle dom.canvas #store the new circle object in the circle array
    circleArray[circleArray.length - 1].paste JSON.parse jsonCopy #paste the copied circle
    selectedCircle = circleArray.length - 1
    alert "Selected newly created Circle # #{selectedCircle}"
    #json = JSON.stringify circle.get()
    #imgToCopy.drawImage destination[0], x, y
  # tooltips: display them to the right of the menu item
  
  #onclick event
  $("#canvas").click (e) ->
    clickedX = e.pageX - @offsetLeft
    clickedY = e.pageY - @offsetTop
    alert clickedX
    alert clickedY
    i = 0
    while i < circleArray.length
      if clickedX < circleArray[i].getRight() and clickedX > circleArray[i].getLeft() and clickedY > circleArray[i].getTop() and clickedY < circleArray[i].getBottom() 
        selectedCircle = i
      i++
      alert "Clicked Circle Number #{selectedCircle}"
    return

  toolTipOptions = 
    position:
      my: 'left top'
      at: 'right+10 top'
    tooltipClass: 'tooltip'
  $(document).tooltip toolTipOptions

###
coffee -cw /Users/jan/parwinr/parvina.co.nf/parvina.co.nf/playground/

###