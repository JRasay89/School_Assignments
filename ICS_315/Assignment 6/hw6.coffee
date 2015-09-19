#Modified By: John Rasay
#School: UH Manoa
#Class: ICS 315
#Semester: Spring 2014
#Assignment 6
$ ->
  attributes =
    elem_layout:
      position: 'absolute'
      background    : '#222'
    elem_border:
      border: 'solid'
      'border-color': 'red'
    numeric:
      'border-width': 5
      top: 85
      right: 50
      bottom: 'auto'
      left: 500
      width: 300
      height: 150
      'line-height' : 20
      'font-size' : 18
    numeric_around:
      margin: 'auto'
      padding: 5
      
  
  inner_attributes =
      elem_layout:
        display: 'inline'
      text_shadow:
        'text-shadow': '5px 5px red'
      textual:
        color         : 'yellow'
        background    : '#222'
        'font-weight' : ''
        'font-style'  : 'italic'
        'text-decoration': ''
        'text-transform': 'capitalize'
        'text-align'  : 'center' 
      
  #$ "[id=\"content\"] for multiple
  dom =
    theDiv:   $ "#content"
    innerContent:   $ "[id=\"innercontent\"]" #store all element with the same id
    controls: $ '#left-control'
    right_controls: $ '#right-control'
  for attribute, value of attributes.numeric_around
    for key in ['top', 'right', 'bottom', 'left']
      attributes.numeric["#{attribute}-#{key}"] = value
  index = 0
  row = null
  addAttribute = (key, value, controller, element, px) ->
    row = $('<div>').addClass('row').appendTo controller if index % 2 is 0
    index++
    $('<label>').html("#{key}: ").appendTo row
    field = $('<input>').attr({type:'text'}).val(value).appendTo row
    update = =>
      value = field.val()
      value = "#{value}px" if px? and value isnt 'auto'
      element.css key, value
    field.on "keydown", (event) -> update() if event.which is 13
    field.on "blur", update
    update()
  $.each attributes.elem_layout, (key,value) -> addAttribute key, value, dom.controls, dom.theDiv
  $.each attributes.elem_border, (key,value) -> addAttribute key, value, dom.controls, dom.theDiv
  $.each attributes.numeric, (key, value) -> addAttribute key, value, dom.controls, dom.theDiv, 'px'
  
  index = 0; #reset
  $.each inner_attributes.elem_layout, (key,value) -> addAttribute key, value, dom.right_controls, dom.innerContent
  $.each inner_attributes.textual, (key,value) -> addAttribute key, value, dom.right_controls, dom.innerContent
  $.each inner_attributes.text_shadow, (key,value) -> addAttribute key, value, dom.right_controls, dom.innerContent
  
###
coffee --compile --watch F:/ics315/315/playground/

coffee -cw /Users/jan/parwinr/parvina.co.nf/parvina.co.nf/playground/
###