package com.cdac.elearning.dto;

public class LoginResponse extends Status{
	private String name;
	private String emailId;
	private String authToken;
	
	public LoginResponse() {
		super();
		
	}
	
	
	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}
