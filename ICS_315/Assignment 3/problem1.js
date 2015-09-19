//Global Array Variable
var nameArray = ["Jeff","Greg","Mark","John","Justin","Kevin"]; //Use in the closure function
//Closure function
function outer(x) {
	var counter = 0;
	var p = $('<p />'); 
	p.text("About to exit...");
	$("#display").append(p);
	//Inner function
	function inner() {
		var p2 = $('<p />');
		p2.text("Counter Value: " + counter + " Parameter Value: " + x);
		$("#display").append(p2);
		//Cancel the setInterval when counter is 5
		if (counter == 5) {
			clearInterval(timer);
		}
		counter++; //Increment counter
		x = nameArray[counter]; //set parameter equal to the array with the index equal to counter
	}
	//Call inner function every 1s
	var timer = setInterval(inner,1000);
}

//Run function when document is ready
$(document).ready(function(){
	outer(nameArray[0]); //Pass the first string value in the array
});
