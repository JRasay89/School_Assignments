package com.the_ultimate_trivia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//This Activity is for test only will not be in Final build, but this is will basically be the same in time attack and survival just with added stuff to the game play.
public class SinglePlayerActivity extends Activity {

	private ArrayList<Question> questions;
	private long randomNum;
	private int currentQuestion;
	private int numberOfQuestions; //For now this is use to display a certain amount of questions, will be change for Time attack and survival mode to unlimited
	
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		questions = new ArrayList<Question>();
		
		//This loads a save state or initialize variables with new values
		if (savedInstanceState != null ) {
			if (savedInstanceState.containsKey("randomNum")) {
				randomNum = savedInstanceState.getLong("randomNum");
			} 
			else {
				randomNum = new Random().nextLong();
			}
			
			if (savedInstanceState.containsKey("currentQuestion")) {
				currentQuestion = savedInstanceState.getInt("currentQuestion");
			} 
			else {
				currentQuestion = 0;
			}
	
		} else {
			// There is no saved instance data, set it all up from scratch
			randomNum = new Random().nextLong();
			currentQuestion = 0;
		}
		
		// Get number of questions this quiz activity should display
		if (null != savedInstanceState && savedInstanceState.containsKey("numberOfQuestions")) {
			numberOfQuestions = savedInstanceState.getInt("numberOfQuestions");
			} else if (null != getIntent().getExtras() && getIntent().getExtras().containsKey("numberOfQuestions")) {
				numberOfQuestions = getIntent().getExtras().getInt("numberOfQuestions");
				} else {
					Log.w("SinglePlayerActivity", "QuizActivity has been started without a specified number of questions. This indicates an error in the way it is being called.");
					numberOfQuestions = 10;
					}

		new LoadQuestionsTask().execute("questions");
	}
	
	@Override
	protected void onSaveInstanceState(final Bundle outState) {
		outState.putLong("randomNum", randomNum);
		outState.putInt("currentQuestion", currentQuestion);
		outState.putInt("numberOfQuestions", numberOfQuestions);
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * Loads questions stored in all files at the specified file path. Returns an empty ArrayList if there are no questions found.
	 * 
	 * @param questionFilePath The file path from which questions should be loaded.
	 * @return An ArrayList of all questions found at the specified path.
	 */
	private ArrayList<String> loadQuestions(final String questionFilePath) {
		final ArrayList<String> questions = new ArrayList<String>();
		try {
			for (final String fileName : getAssets().list(questionFilePath)) {
				final InputStream input = getAssets().open(questionFilePath + "/" + fileName);
				final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				// Populate the questions ArrayList
				String inputLine;
				while (null != (inputLine = reader.readLine())) {
					// Ignore any comments in the question file
					if (!inputLine.startsWith("//") && !(inputLine.length() == 0)) {
							questions.add(inputLine);
					}
				}
			}
		} catch (final IOException e) {
			Log.e("SinglePlayerActivity", "IOException while reading questions from file.", e);
			Toast.makeText(getApplicationContext(), "A Problem was encountered.", Toast.LENGTH_LONG).show();
		}
		return questions;
	}
	
	//Extends AsyncTask, runs in the background
	private class LoadQuestionsTask extends AsyncTask<String, Integer, Void> {
		
		@Override
		protected Void doInBackground(String... params) {
			final ArrayList<String> questionsTemp = new ArrayList<String>();
			
			// Load all questions available at each path given as an argument
			for (String path : params) {
				questionsTemp.addAll(loadQuestions(path));
			}
			
			// Randomise the output order of the questions
			final Random rand = new Random(randomNum);
			Collections.shuffle(questionsTemp, rand);

			// Trim the number of questions to be displayed to the amount set.
			if (numberOfQuestions < questionsTemp.size()) {
				int failedParses = 0;
				questions = new ArrayList<Question>();
				for (String s : questionsTemp.subList(0, numberOfQuestions)) {
					try {
						questions.add(Question.readCSVQuestion(s));
					} catch (final IllegalArgumentException e) {
						failedParses++;
						Log.e("SinglePlayerActivity", "Unable to parse question: " + s, e);
					}
				}

				// If any questions failed to parse, summarise (to the user) how many failed
				if (0 < failedParses) {
					Toast.makeText(getApplicationContext(), String.format(getResources().getQuantityString(R.plurals.question_reading_parse_fail_number, failedParses), failedParses), Toast.LENGTH_LONG).show();
				}
			} else {
				int failedParses = 0;
				questions = new ArrayList<Question>();
				for (String s : questionsTemp) {
					try {
						questions.add(Question.readCSVQuestion(s));
					} catch (final IllegalArgumentException e) {
						failedParses++;
						Log.e("SinglePlayerActivity", "Unable to parse question: " + s, e);
					}
				}

				// If any questions failed to parse, summarise (to the user) how many failed
				if (0 < failedParses) {
					Toast.makeText(getApplicationContext(), String.format(getResources().getQuantityString(R.plurals.question_reading_parse_fail_number, failedParses), failedParses), Toast.LENGTH_LONG).show();
				}
			}

			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			
			// If no questions were loaded, close the Activity
			if (questions.isEmpty()) {
				SinglePlayerActivity.this.finish();
			} else {
				displayQuestion(currentQuestion);
			}
		}
		
		//Method for displaying the questions on screen and getting input from users.
		private void displayQuestion(final int questionID) {
			setContentView(R.layout.single_player_play);
			
			// Display the topic of the question
			final TextView quizTopic = (TextView) findViewById(R.id.quiz_topic);
			quizTopic.setText(questions.get(questionID).getTopic());
			
			// Display the question
			final TextView quizQuestion = (TextView) findViewById(R.id.quiz_question);
			quizQuestion.setText(questions.get(questionID).getQuestion());
			
			// Display the answers to the question
			final ListView quizAnswers = (ListView) findViewById(R.id.quiz_answers);
			
			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(SinglePlayerActivity.this, R.layout.quiz_answer_row, questions.get(questionID).getAnswers().toArray(new String[0]));
			quizAnswers.setAdapter(adapter);
			
			// Set up an OnClickListener for the ListView
			quizAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
					final String selectedAnswer = ((TextView) view).getText().toString();
					if (selectedAnswer.equals(questions.get(questionID).getAnswer())) {
						Toast.makeText(getApplicationContext(), getResources().getString(R.string.answer_correct), Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(), getResources().getString(R.string.answer_incorrect), Toast.LENGTH_SHORT).show();
					}
					
					// Display the next question
					currentQuestion++;
					
					if (currentQuestion <= questions.size() - 1) {
						displayQuestion(currentQuestion);
					} else {
						final Intent i = new Intent(SinglePlayerActivity.this, SinglePlayerFinishedActivity.class);
						startActivity(i);
						
						SinglePlayerActivity.this.finish();
					}
				}
			});
		}
	}

}
