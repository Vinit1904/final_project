package com.cdac.elearning.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class TestCases {

	@Id
	private String _id;
	
	@Transient
	private String courseName;
	
	@Transient
	private String problemName;
	
	private Object input;
	
	private Object output;

	public TestCases() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	public Object getOutput() {
		return output;
	}

	public void setOutput(Object output) {
		this.output = output;
	}
	
	
}
