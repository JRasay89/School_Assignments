/* Access to sources of web pages. (Also called 'scrapping')
 * Note that because browsers don't allow cross-domain requests,
 * and scrapping sources falls under these restrictions, more complicated
 * means are necessary to circumvent such restrictions.
 * 
 * Copyright 2014 Jan Stelovsky, Uni Hawaii, ICS, 
 * MIT license */

// Attempts to get the html source of a web page with 'url'.
// Calls asynchronously onSource(html) if the page's html can be obtained
// otherwise calls onError(message)
// Sample usage:
//   getSource('http://www.mit.edu/index.html', 
//     function(source){
//       // use source
//     }, 
//     function(error){
//       // report error
//     }
//   );
// Note that some web sites do not allow access to their web pages.
// Note: Doesn't support deferred/promise protocol.
// Note: Uses Yahoo's YQL frameworks, i.e., it won't work is YQL isn't available.
function getSource(url, onSource, onError){
  $.ajax({
    type: 'GET', dataType: 'json',
    // url of YQL's service
    url: 'http://query.yahooapis.com/v1/public/yql?callback=?',
    data: { // YQL query
      q: 'select * from html where url="' + url + '" and xpath="*"', 
      format: 'xml'
    },
    // callback upon query's success; note that there may be no data
    success: function(data) {
      if (data.results.length > 0) {
        // extract the page's html and get rid of <script> elements
        text = data.results[0].replace(/<script[^>]+?\/>|<script(.|\s)*?\/script>/gi, '');
        // call client's on success callback
        onSource(text);
      } else { // failiure, call 
        onError("No source; Possibly url doesn't exist or can't be scrapped");
      }
    },
    // callback upon failiure of ajax call, pipe it to client
    error: function(data) {
      onError(arguments[2]);
    }
  });
}


//array to hold the nested objects
var iframeObj = [];
//Function for Problem 1
//Calls the getSource function to get the data from the given url
//Extract iframe information from the data and append it to a textarea on the html page 
function go() {
	//Gets the url entered from the textbox
	var url = $("#url").val();
	getSource(url, 
		function(source){
			//Get the number of iframes
			var length = $(source).find("iframe").length;
			//If no iframes were found display alert message
			if (length == 0) {
				alert("no iframes found");
			}
			//for every iframes get the src,width,height,scrolling, and frameborder value
			//and store it in a nested array of objects
			else {
				for (var i = 0;  i < length; i++) {
					//var name = $(source).find("p").eq(i).text();
					var src = $(source).find("iframe").eq(i).attr("src");
					var width = $(source).find("iframe").eq(i).attr("width");
					var height = $(source).find("iframe").eq(i).attr("height");
					var scrolling = $(source).find("iframe").eq(i).attr("scrolling");
					var frameBorder = $(source).find("iframe").eq(i).attr("frameborder");
					//Removes ?vert=1&width=300 from the iframe src if there is any
					src = src.replace(/\?.*0/g,'');
					//name = name.replace(/by\s+/g,'');
					//Creating the nested objects
					var item = {};
					var iframe = {};
					var pathSrc = {};
					pathSrc["path"] = src;
					iframe["src"] = pathSrc;
					iframe["width"] = width;
					iframe["height"] = height;
					iframe["scrolling"] = scrolling;
					iframe["frameborder"] = frameBorder;
					//item["by"] = name;
					item["iframe"] = iframe;
					//Push the object in the array of objects
					iframeObj.push(item);
				}
				//Display the iframes in a textarea
				for (var i = 0; i < iframeObj.length; i++) {
					var newDiv =  $('<div style="display:inline"/>'); 
					var textArea = $('<textarea style="width:450px; height:250px" />'); 
					textArea.text(JSON.stringify(iframeObj[i], null, ' '));
					newDiv.append(textArea);
					$("#displayResult").append(newDiv);
				}
			}
			
		}, 
		function(error){
			alert(error);
		}
	);
}

//Problem 2
//Created 3 functions, getIframe,getStudentJson, and displayQuiz
//This function takes the name and url of the student website to extract the iframe src data
//Append a new dive and iframe to display the video quizzes in the html page
function getIframe(name,url) {
	getSource(url, 
		function(source){
			var length = $(source).find("iframe").length;
			//If no iframes were found display alert message
			if (length == 0) {
				alert("no iframes found");
			}
			else {
				for (var i = 0;  i < length; i++) {
					var src = $(source).find("iframe").eq(i).attr("src");
					//Removes ?vert=1&width=300 from the iframe src if there is any
					src = src.replace(/\?.*0/g,'');
					//Creates a new div with class name quiz, a new p to hold name of owner of 
					//the video quiz, and an iframe to display the video quizzes. Append the
					//iframe video quizzes to the body.
					var newDiv =  $('<div class="quiz" />'); 
					var p = $('<p/>'); 
					p.text("by: "+name);
					var iframe = $('<iframe src="'+src+'?vert=1&width=300" width="300" height="440" frameborder="no" scrolling="no"/>'); 
					newDiv.append(iframe);
					newDiv.append(p);
					$("body").append(newDiv);
				}
			}
			
			
		}, 
		function(error){
			alert(error);
		}
	);
}

//Takes a jsonFile and calls getIframe to get the video quizzes for each given student sites.
function getStudentJson(jsonFile) {
	$.getJSON(jsonFile, function(data) {
		for (var i = 0; i < data.length; i++) {
			var name = data[i].name;
			var url = data[i].url;
			getIframe(name,url);
		}
	});
}
//Calls the getStudentJson function to display the video quizzes from the given json file 315.json
function displayQuiz() {
	getStudentJson("json/315.json");
}

//When document is ready
$(document).ready(function(){
	//Button function for problem 1
	$("#extract").click(function(){
		go();
	});
	
	//for problem 2 
	$("#show").click(function(){
		displayQuiz();
	});
});