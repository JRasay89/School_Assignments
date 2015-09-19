###
A very primitive class that supports drawing a circle on a HTML5 <canvas>
###
class Circle
  
  # Makes a new circle centered at (400,200) with 100px radius colored cyan with red perimeter in 'canvas'.
  # Draws the circle.
  # 'canvas' must be a jQuery DOM element in a browser that supports HTML5 <canvas>
  constructor: (canvas) ->
    @context = canvas[0].getContext '2d' if canvas[0].getContext?
    @specs =
      x: 400
      y: 200
      radius: 100
      color: 'cyan'
      stroke: 'red'
    @draw()
    
  # Resets the circle according to parameters in 'specs'
  set: (@specs) -> 
    @draw()
    @
  
  # Returns the circle's parameters
  get: -> @specs
  
  # Draws the circle in <canvas>
  draw: ->
    return unless @context?
    # clear the canvas
    @context.setTransform 1, 0, 0, 1, 0, 0
    @context.clearRect 0, 0, @context.canvas.width, @context.canvas.height
    # draw the circle
    @context.beginPath()
    # set colors
    @context.fillStyle = @specs.color
    @context.strokeStyle = @specs.stroke
    # draw circle
    @context.arc @specs.x, @specs.y, @specs.radius, 0, 2 * Math.PI, true
    # draw colors
    @context.fill()
    @context.stroke()
    @

  # Make Circle class accessible from the JS namespace
  window.JS = {} unless window.JS?
  window.JS.Circle = Circle
###
###