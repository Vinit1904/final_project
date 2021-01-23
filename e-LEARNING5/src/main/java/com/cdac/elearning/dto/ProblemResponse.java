package com.cdac.elearning.dto;

public class ProblemResponse {

	private String problemName;
	
	private String difficulty;
	
	private String description;

	public ProblemResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
