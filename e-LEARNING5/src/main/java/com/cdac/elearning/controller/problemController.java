package com.cdac.elearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.elearning.dto.ProblemResponse;
import com.cdac.elearning.dto.Status;
import com.cdac.elearning.dto.Status.StatusType;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Problems;
import com.cdac.elearning.service.ProblemService;

@RestController
@CrossOrigin
public class problemController {

	@Autowired	
	ProblemService problemService;
	
	
	@GetMapping("/findAllproblem")
	public List<ProblemResponse> findAllProblem(@RequestBody Problems problem) {
		try {
			return	problemService.findAllProblem(problem);
		}
		catch(CourseException e) {
			
			List<ProblemResponse> list =new ArrayList();
			return list;
			
		}
	}
	
	@PostMapping("/findOneproblem")
	public ProblemResponse findOneproblem(@RequestBody Problems problem) {
		try {
			return	problemService.findOneProblem(problem);
		}
		catch(CourseException e) {
			
			ProblemResponse list =new ProblemResponse();
			return list;
			
		}
	}
	
	@PostMapping("/problem/add")
	public Status addCourse(@RequestBody Problems problem) {
		try {
			problemService.addProblem(problem);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Problem Successfully Entered!!");
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
	
	
	
	@PostMapping("/problem/update")
	public Status updateCourse(@RequestBody Problems problem) {
		try {
			problemService.updateProblem(problem);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Problem Successfully updated!!");
			return status;
		}
		catch(CourseException e) {
			Status status=new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
			
		}	
	}
	
	@PostMapping("/problem/delete")
	public Status deleteCourse(@RequestBody Problems problem) {
		try {
			problemService.deleteProblem(problem);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Problem Successfully deleted!!");
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
