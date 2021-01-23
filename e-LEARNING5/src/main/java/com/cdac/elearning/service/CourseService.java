package com.cdac.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Problems;
import com.cdac.elearning.model.Quiz;
import com.cdac.elearning.model.Scores;
import com.cdac.elearning.model.User;
import com.cdac.elearning.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	
	public List<Course> findAllCourses() {
		try {
		List<Course> courses=courseRepository.findAll();
		if(courses!=null) {
			return courses;
			}
		else
			throw new CourseException();
		}catch(CourseException e) {
			
			throw new CourseException();
		}
	}
	
	
	public void addCourse(Course course)
	{
		try {			
		
		courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new CourseException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Duplicate Course Name");
		}						
		
	}
	
	public void updateCourse(Course course)
	{
		try {
		Course courses = courseRepository.findByName(course.getCourseName());
			if(courses!=null) {
			courses.setImage(course.getImage());
			courseRepository.save(courses);
			}
			else 	{
				throw new CourseException("Course Not Found");
			}
		}
		catch(CourseException e){
			throw new CourseException("Course Not Found");
		}
		
	}
	
	public void deleteCourse(String name)
	{
		try {
		Course course=courseRepository.findByName(name);
		if(course!=null) {
		courseRepository.delete(course);
		}else {
			throw new CourseException("Course Not Found");
			}		
		}
		catch(CourseException e){
			throw new CourseException("Course Not Found");
		}
		
	}
}
