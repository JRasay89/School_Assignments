package com.the_ultimate_trivia;

import java.util.ArrayList;
import java.util.Collections;

final class Question {
	//Instance Variables
	private final String topic; //The topic of this question
	private final String question; //The question
	private final ArrayList<String> answers; //A list of answers
	
	//Constructor
	private Question(final String topic, final String question, final ArrayList<String> answers) {
		this.topic = topic;
		this.question = question;
		this.answers = answers;
	}
	
	//Getter methods
	public final String getTopic() {
		return topic;
	}
	
	public final String getQuestion() {
		return question;
	}
	
	public final ArrayList<String> getAnswers() {
		// ArryList is not immutable, create a defensive copy
		final ArrayList<String> answers = new ArrayList<String>(this.answers);
		Collections.shuffle(answers);
		return answers;
	}
	
	public final String getAnswer() {
		return answers.get(0);
	}
	
	//reads in the Questions and store it
	public static Question readCSVQuestion(String input) throws IllegalArgumentException {
		
		final String[] questionTokens;
		final String topic;
		final String question;
		final ArrayList<String> answers = new ArrayList<String>();
		// Split the input on commas
		if (input != null) {
			questionTokens = input.split(",");
			} else {
				throw new IllegalArgumentException("Error: Question to be parse was null."); 
				}
				
		// Extract the component parts of the question
		if (4 < questionTokens.length) {
			topic = questionTokens[0].trim();
			question = questionTokens[1].trim();
			
			for (int i = 2; i < questionTokens.length; i++) {
				answers.add(questionTokens[i].trim());
				}
			
		} else {
			throw new IllegalArgumentException("Error: Question has missing parts.");
			}
		
		return new Question(topic, question, answers);
	}
	

}
