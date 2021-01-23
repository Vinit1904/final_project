package com.cdac.elearning.dto;

import java.util.List;

public class QuizResponse {

	private String question;
	
	private String correct_answer;
	
	private List<String> incorrect_answers;

	public QuizResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public List<String> getIncorrect_answers() {
		return incorrect_answers;
	}

	public void setIncorrect_answers(List<String> incorrect_answers) {
		this.incorrect_answers = incorrect_answers;
	}
	
	
}