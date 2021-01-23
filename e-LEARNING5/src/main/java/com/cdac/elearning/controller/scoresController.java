
package com.cdac.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.elearning.dto.Score;
import com.cdac.elearning.dto.ScoreResponse;
import com.cdac.elearning.dto.Status;
import com.cdac.elearning.dto.Status.StatusType;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Scores;
import com.cdac.elearning.service.ScoreService;


@RestController
@CrossOrigin
public class scoresController {

	@Autowired
	ScoreService scoreService;
	
	
	@GetMapping("/getscore")
	public ScoreResponse getScore(@RequestParam("courseName") String name) {
		try {
			
			return scoreService.getScore(name); 
			
		}
		
		catch(CourseException e) {
			ScoreResponse score =new ScoreResponse();
			return score;
		}
		
	}
		
	
	@PostMapping("/score/add")
	public Status addScore(@RequestBody Scores score) {
		try {
			scoreService.addScore(score);
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
	
	
	@PostMapping("/score/update")
	public Status updateScore(@RequestBody Scores score) {
		try {
			scoreService.updateScore(score);
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
	
}
