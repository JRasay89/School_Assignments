/*global $*/
// The first line is for the jslint.com parser. See comment at bottom of file.

/*
 * ICS 215 Assignment 4
 * John Rasay
 */

// Global variables
var countries = [
        { country: 'Afghanistan',   capital: 'Kabul' },
        { country: 'Argentina',     capital: 'Buenos Aires' },
        { country: 'Australia',     capital: 'Canberra' },
        { country: 'Austria',       capital: 'Vienna' },
        { country: 'Belgium',       capital: 'Brussels' },
        { country: 'China',         capital: 'Beijing' },
        { country: 'Colombia',      capital: 'Bogota' },
        { country: 'France',        capital: 'Paris' },
        { country: 'Japan',         capital: 'Tokyo' },
        { country: 'Germany',       capital: 'Berlin' },
        { country: 'India',         capital: 'New Delhi' },
        { country: 'Israel',        capital: 'Jerusalem' },
        { country: 'Liechtenstein', capital: 'Vaduz' },
        { country: 'Mexico',        capital: 'Mexico City' },
        { country: 'Russia',        capital: 'Moscow' },
        { country: 'Seychelles',    capital: 'Victoria' },
        { country: 'Switzerland',   capital: 'Bern' },
        { country: 'Sweden',        capital: 'Stockholm' },
        { country: 'Samoa',         capital: 'Apia' },
        { country: 'Spain',         capital: 'Madrid' },
        { country: 'U.S.A.',        capital: 'Washington D.C.' },
        { country: 'Peru',          capital: 'Lima' },
        { country: 'Philippines',   capital: 'Manila' },
        { country: 'Vanuatu',       capital: 'Port-Vila' },
        { country: 'Vietnam',       capital: 'Hanoi' }
    ],
    numAnswers = 0, // Number of answered questions so far
    numCorrect = 0; // Number of correctly answered questions so far

	
	//Array thats holds the names of the labels
	var label = [{label: 'label0'},
			 {label: 'label1'},
			 {label: 'label2'},
			 {label: 'label3'}];
	//Indexes for getting questions and answers
	var trueIndex = 0;
	var	falseIndex = 0;
	var	falseIndex2 = 0;
	var	falseIndex3 = 0;
	//The correct answer 
	var correctAnswer = "";
	//Variables that holds the statistics
	var questionNumber = 1;
	var correctAnswers = 0;
	var percentCorrect = 0;
	
	

/**
 * Problem 3:
 * Validate the answer from the form:
 * highlight the correct answer (set "correct" class,)
 * update the statistics,
 * disable the validate button (set "disabled" property to true)
 * and enable the "next" button (set "disabled" property to false)
 */
function validateAnswer() {
	var choice = $('[name="choice"]:checked').next("span").text();
	var index = 0;
	if (choice === correctAnswer) {
		//alert("CORRECT");
		correctAnswers+=1; //Increment number answers got correct
		//Calculating the percent correct
		percentCorrect = (correctAnswers/(questionNumber-1))*100;
		percentCorrect = percentCorrect.toFixed(2);
		//Hightlighting the correct answer
		while (index < 4) {
			var value = $("#"+label[index].label).text();
			if (value== correctAnswer) {
				$("#"+label[index].label).attr('class','correct');
				break;
			}
			else {
				index++;
			}
		}
		$("#num_correct").text(correctAnswers);
		$("#pct_correct").text(percentCorrect);
		$("#btncheck").attr('disabled','true');
		$("#btnnext").removeAttr('disabled');
		
	}
	else {
		//Hightlighting the correct answer
		while (index < 4) {
			var value = $("#"+label[index].label).text();
			if (value== correctAnswer) {
				$("#"+label[index].label).attr('class','correct');
				break;
			}
			else {
				index++;
			}
		}
		//Calculating the percent correct
		percentCorrect = (correctAnswers/(questionNumber-1))*100;
		percentCorrect = percentCorrect.toFixed(2);
		$("#pct_correct").text(percentCorrect);
		//Disabling and enabling buttons
		$("#btncheck").attr('disabled','true');
		$("#btnnext").removeAttr('disabled');
	}
}

/**
 * Problem 2:
 * Display the next question,
 * disable the "Next" button and Enables the "Check answer" button
 * Problem 3: Remove highlight of correct question (remove "correct" class,)
 */
function nextQuestion() {
    // Your code
	var index = 0;
	//Setting the first question
	if (trueIndex == 0) {
		//Setting the initial statistic values
		questionNumber = 1;
		correctAnswers = 0;
		percentCorrect = 0;
		$("#num_questions").text(questionNumber);
		$("#num_correct").text(correctAnswers);
		$("#pct_correct").text(percentCorrect);
		getQuestionsIndex(); //Get the false answers
		label.sort(function() { return 0.5 - Math.random() }); //Randomize the values of the label array which contains the id's of the labels
		correctAnswer = countries[trueIndex].capital;
		$("#country").text(countries[trueIndex].country);
		$("#"+label[0].label).text(countries[trueIndex].capital);
		$("#"+label[1].label).text(countries[falseIndex].capital);
		$("#"+label[2].label).text(countries[falseIndex2].capital);
		$("#"+label[3].label).text(countries[falseIndex3].capital);
		trueIndex++; //increment index for the next question
		questionNumber++; //Increment the number of quest
		//Disabling and enabling buttons
		$("#btncheck").removeAttr('disabled');
		$("#btnnext").attr('disabled','true');
	}
	else if (trueIndex < countries.length) {
		//Removing the highlight of correct question
		while (index < 4) {
			var value = $("#"+label[index].label).text();
			if (value== correctAnswer) {
				$("#"+label[index].label).removeAttr('class');
				break;
			}
			else {
				index++;
			}
		}
		getQuestionsIndex(); //Get the false answers
		label.sort(function() { return 0.5 - Math.random() });  //Randomize the values of the label array which contains the id's of the labels
		$("#num_questions").text(questionNumber); //Setting the number of question statistic
		correctAnswer = countries[trueIndex].capital;
		$("#country").text(countries[trueIndex].country);
		$("#"+label[0].label).text(countries[trueIndex].capital);
		$("#"+label[1].label).text(countries[falseIndex].capital);
		$("#"+label[2].label).text(countries[falseIndex2].capital);
		$("#"+label[3].label).text(countries[falseIndex3].capital);
		trueIndex++; //increment index for the next question
		questionNumber++; //Increment the number of quest
		//Disabling and enabling buttons
		$("#btncheck").removeAttr('disabled');
		$("#btnnext").attr('disabled','true');
		
		//Resets Questions and statistics
		if (trueIndex == countries.length) {
			trueIndex = 0;
			
		}
	}
}

/**
  * Function to get three random false answers, keep randomizing until no duplicates
  */
function getQuestionsIndex() {
	do {
		falseIndex = Math.floor((Math.random()*countries.length));
		falseIndex2 = Math.floor((Math.random()*countries.length));
		falseIndex3 = Math.floor((Math.random()*countries.length));
		}
	while (falseIndex === trueIndex || falseIndex === falseIndex2 || falseIndex === falseIndex3|| falseIndex2 === trueIndex || falseIndex2 === falseIndex3 || falseIndex3 === trueIndex);

}

// Initialization
$().ready(function() {
    nextQuestion();
    $('#btncheck').click(validateAnswer);
    $('#btnnext').click(nextQuestion);
});

// It's recommended to copy-paste your code to jslint.com to create clean
// javascript.  Since it's generally bit overly-clean here's a couple of
// relaxing options (automatically set if you leave the last line.)
// Don't worry about "'yourFunction' was used before it was defined,"
// errors should you run into them.
/*jslint browser: true, devel: true, forin: true, plusplus: true, regexp: true, sloppy: true, stupid: true, vars: true, white: true, indent: 4 */
