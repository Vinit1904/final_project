package com.cdac.elearning.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "Test_Quiz")
public class Quiz {

	@Id 
	private String _id;	
	
	@Transient
	private String courseName;
	
	private String question;
	
	private String correct_answer;
	
	private List<String> incorrect_answers;
	

	public Quiz() {
		super();
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


	public String get_id() {
		return _id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	
	
}
