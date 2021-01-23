package com.cdac.elearning.dto;

public class AuthenticationResponse {

	private String authenticationToken;
    
	private String username;
	
	private String firstName;

	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public AuthenticationResponse(String authenticationToken, String username, String firstName) {
		super();
		this.authenticationToken = authenticationToken;
		this.username = username;
		this.firstName = firstName;
	}



	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
}
