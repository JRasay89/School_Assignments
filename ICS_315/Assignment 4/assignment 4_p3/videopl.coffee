#Author: John Rasay
#School: UH Manoa
#Class: ICS 315
#Semester: Spring 2014
#Assignment 4 Problem 3

#class for loading a video
class loadVideo
  #constructor that takes in the video id and the url of the video
  constructor: (@id, @url) ->
    #Check the file type
    #check if file type is ogg
    oggPatt = new RegExp("\.og[gv]")
    oggResult = oggPatt.test @url
    #check if file type is mp4
    mpPatt = new RegExp("\.mp4")
    mpResult = mpPatt.test @url
    #check if file type is webm
    webmPatt = new RegExp("\.webm")
    webmResult = webmPatt.test @url
    
    #change the src attribute
    #if video is ogg
    if oggResult
      $("#oggVid").attr 
        src: @url
      $("##{@id}")[0].load()
    #if video is mp4
    else if mpResult
      $("#mpVid").attr 
        src: @url
      $("##{@id}")[0].load()
    #if video is webm
    else if webmResult
      $("#webmVid").attr 
        src: @url
      $("##{@id}")[0].load()
    else
      alert "Error: Unknown Video Format!"
class videoControls
  #constructor that takes the div container id of the video
   constructor: (@id) ->
     divControl = $("<div id='video-controls' />")
     playButton = $("<button type='button' id='play_pause'>Play</button>")
     restartButton = $("<button type='button' id='restart'>Restart</button>")
     seekButton = $("<input type='range' id='seek_bar' value='0'>")
     divControl.append playButton
     divControl.append restartButton
     divControl.append seekButton
     $("##{@id}").append divControl
   #pausing or playing video
   playPause: (@vidID) ->
     if $("##{@vidID}")[0].paused
       $("##{@vidID}")[0].play()
       $("#play_pause").text("Pause")
     else 
       $("##{@vidID}")[0].pause()
       $("#play_pause").text("Play")
   #restarting video
   restart: (@vidID) ->
     $("##{@vidID}")[0].currentTime = 0
   #seek through the video
   seek: (@vidID, @time) ->
     @time = $("##{@vidID}")[0].duration * @time 
     $("##{@vidID}")[0].currentTime = @time
     
   #update the seek bar when video is playing
   updateSeekBar: (@vidID) ->
     time = (100 / $("##{@vidID}")[0].duration) * $("##{@vidID}")[0].currentTime;
     $("#seek_bar").val(time)
     
   #change button text to play when video finish playing
   updateButton: ->
     $("#play_pause").text("Play")

#when document is ready     
$().ready ->
  #load video when button is click
   $("#enter").click ->
     url = $("#url").val()
     new loadVideo "vidPlayer", url
     
   #create control buttons for the video  
   controls = new videoControls "divVideo"
   #pause or play video when button is click
   $("#play_pause").click ->
     controls.playPause("vidPlayer")
   
   #restart video when button is click
   $("#restart").click ->
     controls.restart("vidPlayer")
   
   #update video when seek bar is change
   $("#seek_bar").change ->
     time = $("#seek_bar").val() / 100
     controls.seek("vidPlayer", time)
   
   #update the seek bar when video is playing
   $("#vidPlayer").bind "timeupdate", ->
     controls.updateSeekBar("vidPlayer")
     
   #change the pause button to play when video is done playing  
   $("#vidPlayer").bind "ended", ->
     controls.updateButton()
