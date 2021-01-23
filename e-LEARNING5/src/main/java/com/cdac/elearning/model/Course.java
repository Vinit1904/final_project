package com.cdac.elearning.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection = "Test_Course")
public class Course {
	
	@Id
	private String _id;
	
	@Indexed(name = "index_unique_courseName", unique = true)
	private String courseName;
	
	@Transient
	private String emailId;
	
	private String image;
		
	private Scores Score;
	
	
	private List<Quiz> quiz;
	
	
	private List<Problems> problems;
	
	public Course( ) {
		super();
		Score= new Scores();
		quiz=new ArrayList<Quiz>();
		problems=new ArrayList<Problems>();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Quiz> quiz) {
		this.quiz = quiz;
	}

	public List<Problems> getProblems() {
		return problems;
	}

	public void setProblems(List<Problems> problems) {
		this.problems = problems;
	}

	public Scores getScore() {
		return Score;
	}

	public void setScore(Scores score) {
		Score = score;
	}

	public String get_id() {
		return _id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	

}
