# Supports drawing shapes into HTML5 canvas.
#
# Copyright 215 Jan Stelovsky, UH ICS
# Free to use by ICS 315 students
#
#Modified By: John Rasay
#School: UH Manoa
#Class: ICS 315
#Semester: Spring 2014

#color class
class Color
  @transparent = 'rgba(0,0,0,0)'
  @red         = 'rgb(255,0,0)'
  @green       = 'rgb(0,255,0)'
  @blue        = 'rgb(0,0,255)'
  @yellow      = 'rgb(255,255,0)'
  @black       = 'rgb(0,0,0)'
  @white       = 'rgb(255,255,255)'
  @gray        = 'rgb(127,127,127)'
  @aqua        = 'rgb(0,255,255)'
  # partially transparent
  @redish      = 'rgba(255,0,0,0.25)'
  @greenish    = 'rgba(0,255,0,0.25)'
  @bluish      = 'rgba(0,0,255,0.25)'

class Canvas
  
  # make Canvas class accessible by outside programs
  # so that the can instantiate a canvas with canvas = new Canvas()
  window.Canvas = Canvas
  
  # Creates a canvas for <canvas> with id 'id'
  constructor: (id) ->
    
    # Draws a semi-transparent grid so that drawing can be visually tested
    grid = (n, d, max) =>
      # n: number of gridlines; gap: in between them; length: their length
      [n, gap, length] = [50, 50, 500] 
      # vertical gridlines
      for x in [-n..n]
        # highlight axis in red
        color = if x is 0 then Color.redish else Color.greenish 
        new Line(@, [gap * x, -length], [gap * x, length]).stroke(color).draw()
      # horizontal gridlines
      for y in [-n..n]
        # highlight axis in red
        color = if y is 0 then Color.redish else Color.bluish
        new Line(@, [-length, gap * y], [length, gap * y]).stroke(color).draw()
    
    # if canvas is supported and exists define context tp draw into and draw grid
    # otherwise complain
    canvas = $("##{id}").get 0
    @unsupported = not canvas?
    if @unsupported
      "System error: No <canvas id=\'#{id}\" /> DOM element."
    else
      @unsupported = not canvas.getContext?
      if @unsupported
        "Sorry, your browser doesn't support drawing. (I.e., HTML5 canvas)"
      else
        @context = canvas.getContext '2d'
        grid()
  
  # Converts angle into radians
  # 'angle' must be an object with either 'degrees' or 'radians' property
  # Note: static function, i.e. can be called as Canvas.toRadians(angle)
  @toRadians: (angle) ->
    if angle.degrees? 
      Math.PI / 180 * angle.degrees
    else # nothing to convert
      angle.radians
    
# Superclass for lines, circles and other shapes
class Shape
  
  # Defines defaults: colors of stroke and for filling, line width, scale width and height, translate x and y
  constructor: (@canvas) ->
    @context = @canvas.context
    @color = Color.transparent
    @strokeColor = Color.black
    @lineWidth = 1
    @scaleWidthHeight = [1,1]
    @translateXY = [1,1]
   
  # Sets the fill color for subsequent drawing
  fill: (@color) -> @
    
  # Sets the stroke color for subsequent drawing
  stroke: (@strokeColor) -> @
  
  # Sets the stroke width for subsequent drawing
  strokeWidth: (@lineWidth) -> @
  
  #Sets the angle for rotating drawing
  rotate: (@angle) -> @
  
  #scale shape
  scale: (@scaleWidthHeight) -> @
  
  #translate shape
  translate: (@translateXY) -> @
  
  # Draws the shape
  drawShape: (drawSpecfic) ->
    return if @canvas.unsupported
    # TO DO: perform the move, rotation, scaling
    # preliminaries; 
    # note: all the subclasses need this, but it should be done in one place only
    @context.save()
    @context.fillStyle = @color
    @context.strokeStyle = @strokeColor
    @context.lineWidth = @lineWidth
    @context.beginPath()
    #Rotating, Scaling and Translating
    @context.rotate @angle*Math.PI/180
    @context.scale @scaleWidthHeight[0], @scaleWidthHeight[1]
    @context.translate @translateXY[0], @translateXY[1]
    # let subclass draw the main part
    drawSpecfic()  
    # finish the shape; again, saves subclasses these chores! 
    @context.closePath()
    @context.fill()
    @context.stroke()
    # reset the canvas fro subsequent drawing
    @context.restore()
    # alternative reset: 
    #   @context.setTransform 1, 0, 0, 1, 0, 0
    @
    
# Line between 'from' and 'to' points 
class Line extends Shape
  
  # Makes the line
  # Note: stores the from and to corners as properties of this line
  constructor: (canvas, @from, @to) -> super canvas
  
  # Draws this line
  # Note: the superclass does the common chores!
  draw: =>
    # Draws the line-specific portion
    @drawShape => 
      @context.moveTo @from[0], @from[1]
      @context.lineTo @to[0], @to[1]
      
# Circle
class Circle extends Shape
  
  # Makes the circle
  # Note: stores the center and radius as properties of this circle
  constructor: (canvas, @center, @radius) -> super canvas
  
  # Draws this circle
  # Note: the superclass does the common chores!
  draw: =>
    @drawShape =>
      @context.arc @center[0], @center[1], @radius, 0, 2 * Math.PI, true
 
#Rectangle
class Rectangle extends Shape
  constructor: (canvas, @xy, @wh) -> super canvas
  
  # Draws this rectangle
  # Note: the superclass does the common chores!
  draw: =>
    @drawShape =>
      @context.rect @xy[0], @xy[1], @wh[0], @wh[1]

#Rounded Corner Rectangle
class roundRectangle extends Shape
  #Draws this rounded rectangle
  constructor: (canvas, @x, @y, @width, @height, @radius) -> super canvas
  
  draw: =>
    @drawShape =>
      @context.moveTo @x + @radius, @y
      @context.lineTo @x + @width - @radius, @y
      @context.quadraticCurveTo @x + @width, @y, @x + @width, @y + @radius
      @context.lineTo @x + @width, @y + @height - @radius
      @context.quadraticCurveTo @x + @width, @y + @height, @x + @width - @radius, @y + @height
      @context.lineTo @x + @radius, @y + @height
      @context.quadraticCurveTo @x, @y + @height, @x, @y + @height - @radius
      @context.lineTo @x, @y + @radius
      @context.quadraticCurveTo @x, @y, @x + @radius, @y
#Square  
class Square extends Rectangle
  draw: =>
    @drawShape =>
      @context.rect @xy[0], @xy[1], @wh, @wh
#Polygon
class Polygon extends Shape
  
  # Makes the Polygon
  # Note: takes points of the polygon and store them in a multidimensional array
  constructor: (canvas, @points) -> super canvas
    
  draw: =>
    @drawShape =>
      i = 1
      @context.moveTo @points[0][0], @points[0][1]
      while i < @points.length
        @context.lineTo @points[i][0], @points[i][1]
        i++
#Curve           
class Curve extends Shape
  constructor: (canvas, @startP,@cp1, @cp2, @xy) -> super canvas
  
  # Draws this Curve
  # Note: the superclass does the common chores!
  draw: =>
    @drawShape =>
      @context.moveTo @startP[0], @startP[1]
      @context.bezierCurveTo @cp1[0], @cp1[1], @cp2[0], @cp2[1], @xy[0], @xy[1]
# Once the page is constructed, draw a few test shapes
# TO DO: More shapes to test
$().ready ->
  #Problem 1 Drawing
  canvas = new Canvas 'robot'
  #drawing a drobot
  new Polygon(canvas, [[150,0],[50,50],[250,50]]).fill(Color.red).draw()
  new Rectangle(canvas, [50,50], [200,100]).fill(Color.white).draw()
  new Circle(canvas, [100,75], 20).fill(Color.blue).draw()
  new Circle(canvas, [200,75], 20).fill(Color.blue).draw()
  new Square(canvas,[137,90], 25).fill(Color.green).draw()
  new Line(canvas, [150, 90], [150, 115]).draw()
  new Curve(canvas, [125, 125], [125, 150], [175, 150], [175, 125]).fill(Color.black).draw()
  #Problem 2 Shapes
  #
  #Rectangle Example
  rectCanvas = new Canvas 'rectangle'
  new Rectangle(rectCanvas, [50,0], [200,100]).fill(Color.yellow).draw()
  #Round Rectangle Example
  roundRectCanvas = new Canvas 'roundRect'
  new roundRectangle(roundRectCanvas, 100, 5, 100, 100, 20).fill(Color.aqua).draw()
  #Translating, Rotation and Scaling using Square shape Example
  trsCanvas = new Canvas 'trs'
  new Square(trsCanvas,[0,0], 25).fill(Color.green).draw()
  new Square(trsCanvas,[0,0], 25).translate([25,25]).scale([2,2]).fill(Color.green).draw()
  new Square(trsCanvas,[0,0], 25).translate([75,0]).scale([2,2]).rotate(25).fill(Color.green).draw()
  #lines Example w/ stroke and strokewidth
  lines = new Canvas 'line'
  new Line(lines, [0, 0], [150, 115]).draw()
  new Line(lines, [150, 0], [150, 115]).stroke(Color.red).strokeWidth(10).draw()
  #Polygons Example
  polygon = new Canvas 'polygon'
  new Polygon(polygon, [[150,0],[50,50],[250,50]]).fill(Color.green).draw() #Triangle
  new Polygon(polygon,[[150,50],[100,75],[125,125],[175,125],[200,75]]).fill(Color.red).draw() #Pentagon
  #Bezier Curve Example
  curves = new Canvas 'curves'
  new Curve(curves, [125, 125], [125, 150], [175, 150], [175, 125]).fill(Color.black).draw()
  new Curve(curves, [0, 50], [50, 0], [100, 50], [100, 50]).draw()
  #Circles
  circle = new Canvas 'circles'
  new Circle(circle, [50,75], 50).fill(Color.blue).draw()
  new Circle(circle, [200,75], 10).fill(Color.blue).draw()
