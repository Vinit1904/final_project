package com.cdac.elearning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Scores {

	@Id 
	private String _id;	
		
	@Transient
	private String courseName;
	
	private String quizScore;
	
	private String problemSolvingScore;

	
	public Scores() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQuizScore() {
		return quizScore;
	}

	public void setQuizScore(String quizScore) {
		this.quizScore = quizScore;
	}

	public String getProblemSolvingScore() {
		return problemSolvingScore;
	}

	public void setProblemSolvingScore(String problemSolvingScore) {
		this.problemSolvingScore = problemSolvingScore;
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
