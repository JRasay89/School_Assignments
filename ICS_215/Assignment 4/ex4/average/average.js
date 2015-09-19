/*
 * ICS 215 Assignment 4
 * John Rasay
 */

	//GLOBAL ARRAY VARIABLE
	var avgNum = new Array();
	
	
	//CALCULATING THE AVERAGE AND STANDARD DEVIATION
	function averagedev() {
		 var avrg = average();
		 var standarddev = stddev();
		 var arrayresult = [avrg,standarddev];
		 
		 //For Printing to page
		 $('#avg').val(arrayresult[0]);
		 $("#stddev").val(arrayresult[1]);
		 
		 //Returning the array
		 return arrayresult;
	}
	
	/*
	 * Function for getting the average of the given numbers
	*/
	function average() {
		var sum = 0;
		if (avgNum.length != 0) {
			for (var i = 0; i < avgNum.length; i++) {
				sum = sum + parseInt(avgNum[i]);
			}
		}
		return sum/avgNum.length;
	}

	/*
	 * Function for getting the standard deviation of the given numbers
	*/
	function stddev() {
		var stddev = 0;
		var avg = average();
		if (avgNum.length != 0) {
			for (var i = 0; i < avgNum.length; i++) {
				stddev = stddev + Math.pow((parseInt(avgNum[i])-avg),2);
			}
		}
		return Math.sqrt(stddev/avgNum.length);
	}
	
	//Clearing the html values and array
	function clearNumbers()
	{
		avgNum = new Array();
		$("#numbers").html("");
		$('#avg').val("");
		$("#stddev").val("");
	}
	
	
$().ready(function() {
   //BUTTONS
	//GETS the numbers and push it to the array
	$('#enter').click(function() {
		var n = $("#n").val();
		if (n != 0) {
			avgNum.push(n);
			$("#numbers").append(n+"<br>");
		}
	});
	//Calculates the average and stddev
	$('#calculate').click(function() {
			averagedev();
	});
	
	//Button to reset everything
	$('#clear').click(function() {
			clearNumbers()
	});
});
