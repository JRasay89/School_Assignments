/*global $*/
// The first line is for the jslint.com parser. See comment at bottom of file.

/*
 * ICS 215 Assignment 5
 * John Rasay
 */


/* Problem 1:
 */
function fobj() {
    // variable declarations
    var myjson = '{"is-an-a-student":true,"numbers":[2,1,5],"nested":{"text":"ICS 215"}}',
        myobj = null,
        doSomething = function(a) {
            if (a && a.numbers && a.nested.text) {
                console.log("nested.text:" + a.nested.text);
                console.log("numbers:" + JSON.stringify(a.numbers));
                console.log(a["is-an-a-student"]);
            } else {
                console.error("something's wrong");
            }
        };
	myobj = $.parseJSON(myjson); // parsing JSON object
	doSomething(myobj); // Passing myobj to doSomething
    // function body:
    // extract myjson into myobj. Then pass myobj to doSomething (call doSomething
    // with the extracted object as argument)
}

/**
 * Problem 2:
 */
function loops() {
    // use jquery's each function to print the content of the list items to the console
	$("#list li").each(function(i) {
		console.log(i + ": " + $(this).text());
	});
	console.log('print to the console');
}

/**
 * Problem 3:
 */
function circles() {
    // draw a circle into canvas #drawingbox every time the user clicks it
    // the circle center must shift with every click (unless the center is on
    // the cursor position - the extra credit work)
	
	var x = arguments[0].pageX - this.offsetLeft;  //EXTRA CREDIT
	var y = arguments[0].pageY - this.offsetTop;
	
	//DRAWING THE CIRCLE
	var ctx=this.getContext("2d");
	ctx.beginPath();
	ctx.arc(x,y,40,0,2*Math.PI);
	ctx.stroke();
	//Print to console
    console.log('circles');
}

// Initialization
$().ready(function() {
    console.log('fobj');
    fobj();
    console.log('loops');
    loops();
    $('#drawingbox').click(circles);
});

// It's recommended to copy-paste your code to jslint.com to create clean
// javascript.  Since it's generally bit overly-clean here's a couple of
// relaxing options (automatically set if you leave the last line.)
// Don't worry about "'yourFunction' was used before it was defined,"
// errors should you run into them.
/*jslint browser: true, devel: true, forin: true, plusplus: true, regexp: true, sloppy: true, stupid: true, vars: true, white: true, indent: 4 */
