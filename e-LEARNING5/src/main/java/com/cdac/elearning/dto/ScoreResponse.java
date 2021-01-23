package com.cdac.elearning.dto;

public class ScoreResponse {

	private String quizScore;
	
	private String problemSolvingScore;

	public ScoreResponse() {
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

}
