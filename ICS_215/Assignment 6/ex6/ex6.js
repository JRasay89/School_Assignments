/*
 * ICS 215 Assignment 6
 * John Rasay
 */
 
// Load the Visualization API and the gauge package.
google.load('visualization', '1', {packages: ['gauge']});

//Global Variables
var angle = -60, //Starting Angle
	start = false, //Boolean variable for starting or stopping the program, default false
	rpm = 0,	//Variable for the current rpm value
	min = -1,	//minimum value
	max = 1,	//maximum value
	myTimer,	//Timer
	data,		//Variable for the gauge
	options,	//Variable for the options of the gauge
	chart;		//Variable for the chart

//Called by the start engine and stop engine button	
function startEngine(x) {
	start = x;
	console.log(start); //Debugging, to check if it started or stopped
	
	//If true starts the gauge animation, else stop the animation.
	if (start) {
		myTimer = setInterval(function(){updateEngine(getRPM())},1000);
	}
	else {
		clearInterval(myTimer);
	}
}

//Called by the updateEngine function
function getRPM() {
	//Change the value range so that it is between 0 and 5000, increase angle, and then return rpm
	//If negative add 1 else subtract from 2
	rpm = rpmValue(angle);
	if (rpm < 0) {
		rpm += 1;
	}
	else {
		rpm = 2 - rpm;
	}
	//Set it to two decimal places
	rpm = rpm.toFixed(2);
	rpm *= 2500;
	angle += 20;
	return rpm;
}

//Sinus function to get values for simulating engine
function rpmValue(a) {
	var num;
	num = Math.sin(a * Math.PI/180);
	//Return a number that is between -1 and 1
	if (num >= min && num <= max) {
		return num;
	}
	//In the case value is greater than 1 or less than -1, return 0
	else {
		return 0;
	}
}
		
//Function use to update and animate the RPM Gauge
function updateEngine(val){
	data.setValue(0, 1, parseInt(val));
	chart.draw(data, options);
}	

// When DOM and Visualization API is ready, and data is loaded draw the gauge
$().ready(function(){
	// DOM is ready. When Visualization API is ready load data to draw
	google.setOnLoadCallback(drawChart);
	
	// Create a gauge, 
    // passes in the data and draws it.
	function drawChart() {
		//Create the gauge, start at 0
		data = google.visualization.arrayToDataTable([
			['Label', 'Value'],
			['RPM', 0],
		]);
		//options for the gauge
		options = {
			width: 800, height: 420,
			min: 0,
			max: 6000,
			redFrom: 5000, redTo: 6000,
			yellowFrom: 4000, yellowTo: 5000,
			animation:{
				duration: 1000,
				easing: 'in',
			},
			minorTicks: 5,
			majorTicks: ["0k","1k","2k","3k","4k","5k","6k"]
		};
			
		//Draws the gauge and passes in the options
		chart = new google.visualization.Gauge(document.getElementById('engine'));
		chart.draw(data,options);
	}
});