package com.cdac.elearning.dto;

public class Score {

	private String CourseName;
	
	private String quizScore;
	
	private String problemSolvingScore;

	public Score() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
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

}
