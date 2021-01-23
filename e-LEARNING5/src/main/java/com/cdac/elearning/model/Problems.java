package com.cdac.elearning.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

/*pname
difiiculty
course
tag
description

collection->> test cases
	1)-Input
	->output*/
public class Problems {
	
	@Id
	private String _id;
	
	@Transient
	private String courseName;
	
	private String problemName;
	
	private String difficulty;
	
	private String description;
	
	private List<TestCases> testCases;

	public Problems() {
		super();
		
		testCases=new ArrayList<TestCases>();
	}

	
	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public List<TestCases> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCases> testCases) {
		this.testCases = testCases;
	}

	public String get_id() {
		return _id;
	} 
	
	
	
}


