###
Makes a page with all the students' video quizzes
Copyright Jan Stelovsky UH ICS
MIT license, i.e. free to use even in the assignments or midterm makeup!
###

#Modified By: John Rasay
#School: UH Manoa
#Class: ICS 315
#Semester: Spring 2014
#Assignment 5

# Once the page DOM is ready make the page
$().ready ->
  dom =
    container:  $ '#container'
  #if account object is stored in local storage
  if localStorage.getItem("accounts") isnt null
    console.log "From local storage"
    accounts = JSON.parse(localStorage.getItem("accounts"))
    students = null
    sortAccounts = ->
      for account, student of accounts
        # maintain the UH account (in the key) as field in student's info 
        student.account = account
        if not students? # the 1st student, i.e. array collecting students doesn't exist
          students = [student] # initialize the array
        else # not the 1st student, find the index where to insert the student
          for i in [0...students.length]  # traverse the array
            if students[i].quizzes.length < student.quizzes.length
              # i is the index to insert the student, insert it
              students.splice i, 0, student
              # indicate that the student has been inserted and exit the loop
              student = null
              break
          # if the student hasn't been inserted append it at the end
          students.push student if student?
      accounts # return the sorted students' array
        
      # sort the students according to number of quizzes
    sortAccounts()
    
    for student in students
      # make the row <div> for the 'student'
      studentDiv = $('<div>').attr({id:student.account}).addClass('student').appendTo dom.container
      # add the student's name in a <p> paragraph as the title of the row 
      $('<h2>').addClass('name').html(student.name).appendTo studentDiv
      # as the quizzes in the 'accounts' are in chronogical order from earliest to latest
      # and we want to display them so that the later ones are to the left
      # reverse the order of the quizzes
      # Note: we could sort them based on the 'no' key to make sure
      student.quizzes.reverse()
      # make the thumbnails for each of the student's quizzes
      $.each student.quizzes, (index, quiz) ->
        # make the <div> holding the quiz display
        quizDiv = $('<div>').attr({id:"account_#{quiz.no}"}).addClass('quiz').appendTo studentDiv
        # add the quiz caption in a <p> paragraph as the title of the quiz 
        $('<p>').addClass('caption').html(quiz.caption).appendTo quizDiv
        # add the thumbnail's <img> element
        # Note: the alt attribute will be the YouTube id; displayed if the video
        #   and its metadata isn't available anymore
        $('<img>').attr({src:quiz.image, alt:quiz.yt_id}).appendTo quizDiv
        #When user clicks on thumbnail display object in textarea
        quizDiv.click =>
          #Creating the account object 
          accObj = {}
          #account name property
          accObj["name"] = quizDiv.parent().children(".name").text()
          #the array containing quiz info selected from the thumbnail
          quizObj = []
          #the quiz properties
          item = {}
          item["no"] = quiz.no
          item["game"] = quiz.game
          item["image"] = quiz.image
          item["caption"] = quiz.caption
          item["url"] = quiz.url
          item["yt_id"] = quiz.yt_id
          #push the object in the array
          quizObj.push(item)
          #set quizzes property value
          accObj["quizzes"] = quizObj
          #print the object in a text area
          jsonString = JSON.stringify accObj
          $("#accounts").val(jsonString)
           
  #else read from file and store in local storage
  else 
    console.log "From JSON FILE"
    $.getJSON 'accounts.json', (accounts) ->
      students = null
      sortAccounts = ->
        for account, student of accounts
          # maintain the UH account (in the key) as field in student's info 
          student.account = account
          if not students? # the 1st student, i.e. array collecting students doesn't exist
            students = [student] # initialize the array
          else # not the 1st student, find the index where to insert the student
            for i in [0...students.length]  # traverse the array
              if students[i].quizzes.length < student.quizzes.length
                # i is the index to insert the student, insert it
                students.splice i, 0, student
                # indicate that the student has been inserted and exit the loop
                student = null
                break
            # if the student hasn't been inserted append it at the end
            students.push student if student?
        accounts # return the sorted students' array
        
      # sort the students according to number of quizzes
      sortAccounts()
      #Store the account object in local storage
      jsonString = JSON.stringify accounts
      localStorage.setItem("accounts", jsonString)
      # make rows of thumbnails for each student
      for student in students
        # make the row <div> for the 'student'
        studentDiv = $('<div>').attr({id:student.account}).addClass('student').appendTo dom.container
        # add the student's name in a <p> paragraph as the title of the row 
        $('<h2>').addClass('name').html(student.name).appendTo studentDiv
        # as the quizzes in the 'accounts' are in chronogical order from earliest to latest
        # and we want to display them so that the later ones are to the left
        # reverse the order of the quizzes
        # Note: we could sort them based on the 'no' key to make sure
        student.quizzes.reverse()
        # make the thumbnails for each of the student's quizzes
        $.each student.quizzes, (index, quiz) ->
          # make the <div> holding the quiz display
          quizDiv = $('<div>').attr({id:"account_#{quiz.no}"}).addClass('quiz').appendTo studentDiv
          # add the quiz caption in a <p> paragraph as the title of the quiz 
          $('<p>').addClass('caption').html(quiz.caption).appendTo quizDiv
          # add the thumbnail's <img> element
          # Note: the alt attribute will be the YouTube id; displayed if the video
          #   and its metadata isn't available anymore
          $('<img>').attr({src:quiz.image, alt:quiz.yt_id}).appendTo quizDiv
          #When user clicks on thumbnail display object in textarea
          quizDiv.click =>
            #Creating the account object 
            accObj = {}
            #account name property
            accObj["name"] = quizDiv.parent().children(".name").text()
            #the array containing quiz info selected from the thumbnail
            quizObj = []
            #the quiz properties
            item = {}
            item["no"] = quiz.no
            item["game"] = quiz.game
            item["image"] = quiz.image
            item["caption"] = quiz.caption
            item["url"] = quiz.url
            item["yt_id"] = quiz.yt_id
            #push the object in the array
            quizObj.push(item)
            #set quizzes property value
            accObj["quizzes"] = quizObj
            #print the object in a text area
            jsonString = JSON.stringify accObj
            $("#accounts").val(jsonString)
      null # return less of nonsense in translated JavaScript
    
###
coffee -cmw /Users/jan/Sites/315/video_quizzes/quizzes
###