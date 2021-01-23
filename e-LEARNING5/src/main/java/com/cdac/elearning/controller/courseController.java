package com.cdac.elearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.elearning.dto.Status;
import com.cdac.elearning.dto.Status.StatusType;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.service.CourseService;

@RestController
@CrossOrigin
public class courseController {

	@Autowired
	CourseService courseService;
	
	@GetMapping("/course/findAll")
	public List<Course> findAllCourses(){
		try {
			List<Course> courses= courseService.findAllCourses();
			return courses;
		}catch(CourseException e) {
			List<Course> course = new ArrayList<Course>();
			return course;
		}
		
	}
	
	
	@PostMapping("/course/add")
	public Status addCourse(@RequestBody Course course) {
		try {
			courseService.addCourse(course);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Course Successfully Entered!!");
			return status; 
			
		}
		catch(DuplicateKeyException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
		catch(CourseException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
		
	}
	
	@PostMapping("/course/update")
	public Status updateCourse(@RequestBody Course course) {
		try {
			courseService.updateCourse(course);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Course Successfully updated!!");
			return status;
		}
		catch(CourseException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
			
		}
	
	}
	
	
	@DeleteMapping("/course/delete/{name}")
	public Status deleteCourse(@PathVariable String name) {
		try {
		courseService.deleteCourse(name);
		Status status=new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Course Successfully updated!!");
		return status;
		}
		catch(CourseException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
			
		}
		
	}
	
	
}
