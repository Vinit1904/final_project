package com.cdac.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.elearning.dto.ScoreResponse;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Scores;
import com.cdac.elearning.repository.CourseRepository;
import com.cdac.elearning.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	CourseRepository courseRepository;
	

	public void addScore(Scores score)
	{
		try {
		Course courses = courseRepository.findByName(score.getCourseName());
		Scores mScore= courses.getScore();
		mScore.setProblemSolvingScore(score.getProblemSolvingScore());
		mScore.setQuizScore(score.getQuizScore());
		courses.setScore(mScore);
		courseRepository.save(courses);
		}
		catch(Exception e) 
		{
			throw new CourseException("Course not found");
		}
	}
	
	
	
	
	public ScoreResponse getScore(String name)
	{
		
		Course courses = courseRepository.findByName(name);
		Scores mScore= courses.getScore();
		
		ScoreResponse score=new ScoreResponse();
		score.setProblemSolvingScore(mScore.getProblemSolvingScore());
		score.setQuizScore(mScore.getQuizScore());
		return score;
	}
	
	
	public void updateScore(Scores score)
	{
		Course courses = courseRepository.findByName(score.getCourseName());
		Scores mScore= courses.getScore();		
		mScore.setProblemSolvingScore(score.getProblemSolvingScore());
		mScore.setQuizScore(score.getQuizScore());
		courses.setScore(mScore);
		courseRepository.save(courses);
		
	}
}
