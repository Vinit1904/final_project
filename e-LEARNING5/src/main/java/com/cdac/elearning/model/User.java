package com.cdac.elearning.model;



import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "Test_User")
public class User {
	
	@Id 
	private String _id;
	
	private String firstName;
	
	private String lastName;
	
	@Indexed(name = "index_unique", unique = true)
	private String emailId;
	
	private String mobileNo;
	
	private String password;
	
	private String role;
	
	private String reset_password_token;
	
	private List<Course> courses;


	public User() {
		courses=new ArrayList<Course>();
	}


	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReset_password_token() {
		return reset_password_token;
	}

	public void setReset_password_token(String reset_password_token) {
		this.reset_password_token = reset_password_token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	public String get_id() {
		return _id;
	}
	
	
}
