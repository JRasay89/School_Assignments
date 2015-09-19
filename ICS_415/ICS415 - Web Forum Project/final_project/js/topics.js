//GLOBAL VARIABLES
var is_login = true; //Boolean variable use to check if user is login or a guess, false=guess, true=user's login
var username; //Use to hold the user name
var page; //Use to hold what page the user is on
var table; //Use to hold name of table
var val; //Use to hold name of the topic
var display; //Change what table to display, which is from threads to messages. true=threads, false=messages
var message;
var title;
var href; //Use for back button

//When document is ready
$(document).ready(function(){
    //Check what page is being viewed
    page = $("#title").text();
    //Default hide the form_topic
    $("#form_topic").hide();
	
	//Calls the function checkPage
	checkPage();

	//Check if user is log in or a guest
	$.ajax(
	{
		type:'get',
		cache:false,
		async:false,
		url:'check.php',
		success:function(output)
		{
			//will return false or a username
			if(output === 'false')
			{
				//if it is false then that means no one is logged in
				//so send them to the home page to login/register
				window.location.href = 'home.html';
			}
			else
			{
				//if it gets back something that isn't false then
				//display the welcome message
				$("#welcome_message").html("Welcome, " + output);
				username = output;
			}
		}
	});
	
	//If user is a guess set is_login to false
	if(username == 'guest')
	{	
		is_login = false;
	}
   
   //If true show new topic button and the post button, else hide them
  if (is_login) {
		$("#btn_topic").show();
		$("#post_msg").show();
  }
  else {
		$("#btn_topic").hide();
		$("#post_msg").hide();
  }
  //Shows the form 
  $("#btn_topic").click( function () {
		$("#form_topic").show();
  });
  
  //Resets value of form and hide it
  $("#btn_form_cancel").click( function () {
		$("#inputTitle").val("");
		$("#inputMessage").val("");
		$("#form_topic").hide();
  });
  
  //When click, calls the createTopic function which creates the new topic, store in the database, and show the message table
  $("#form_btns").on('click',"#btn_form_create", function () {
		createTopic()
  });
  
  //When click show the message on the table and store in database
  $("#form_btns").on('click',"#post_btn", function () {
		postMessage();
  });
   

  //When click, show the messages posted to that thread
  $(".table").on('click',"#thread", function(){
    val= $(this).text();
	changeFormBtn();
	$("#form_topic").hide();
	//alert(val);
	display = "messages";
	//Change the panel title of the table to the threads title
	$("#table_title").text(val);
	$.post( 
			"message_handler.php",
			{ val:val, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
  });


});


//Function for checking what page is being viewed and initialize the some of the global variables 
function checkPage() {
	//If the page being viewed is the gaming page, display the table
   if (page === "Games") {
		href = "games.html";
		table = "gaming";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
   //else If the page being viewed is the hardware page, display the table
   else if (page === "Hardware") {
		href = "hardware.html";
		table = "hardware";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
   //else If the page being viewed is the movies page, display the table
   else if (page === "Movies") {
		href = "movies.html";
		table = "movies";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
   //else If the page being viewed is the music page, display the table
   else if (page === "Music") {
		href = "music.html";
		table = "music";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
	//else If the page being viewed is the comics page, display the table
   else if (page === "Comics") {
		href = "comics.html";
		table = "comics";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
   //else If the page being viewed is the other page, display the table
   else if (page === "Other") {
		href = "other.html";
		table = "other";
		display = "thread";
		//Set the title of the table
		$("#table_title").text("Threads");
		$.post( 
			"message_handler.php",
			{ table: table, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
   }
}

//Function to create new topics
function createTopic() {
	title = $("#inputTitle").val();
	msg = $("#inputMessage").val();
	
	//Pop up message if user does not enter anything or enter spaces
	if ($.trim(title) === "") {
		$.msgBox({
			title:"Error",
			type:"error",
			content:"Please enter a Title."
		});
	}
	else if ($.trim(msg) === "") {
		$.msgBox({
			title:"Error",
			type:"error",
			content:"Please enter a message."
		});
	}
	else {
		//Sets display to new topic
		display = "new_topic";
		changeFormBtn();
		$("#table_title").text(title);
		$("#inputMessage").val("");
		$("#form_topic").hide();
		
		//Change the panel title of the table to the threads title
		$("#table_title").text(val);
		$.post( 
			"message_handler.php",
			{title:title, msg:msg, table:table, username:username, display:display},
				function(data) {
					$('.table').html(data);
				}
		);
	}
}

//Function to post new messages to the database
function postMessage() {
	msg = $("#inputMessage").val();
		
		if ($.trim(msg) === "") {
			$.msgBox({
				title:"Error",
				type:"error",
				content:"Please enter a message."
			});
		}
		
		else {
			//Sets display to new topic
			display = "new_message";
			$("#inputMessage").val("");
			$("#form_topic").hide();
			
			//Change the panel title of the table to the threads title
			$("#table_title").text(val);
			$.post( 
				"message_handler.php",
				{val:val, msg:msg, table:table, username:username, display:display},
					function(data) {
						$('.table').html(data);
					}
			);
		}
}
//Change topic button to post button, removes the input title, and change the id of the create button
function changeFormBtn() {
	//Change the back button to return to the previous page
	$("#back_btn").attr("href", href);
	//Change the new topic button into post and its id to post_msg
	$("#form_title").text("Post Message");
	var your_div = document.getElementById("btn_topic");
	var text_to_change = your_div.childNodes[1];
	text_to_change.nodeValue = ' Post';
	$("#btn_topic").attr("id","post_msg");
	$("#inputTitle_div").remove();
	$("#btn_form_create").attr("id","post_btn");
}