package com.cdac.elearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.elearning.dto.Question;
import com.cdac.elearning.dto.QuizResponse;
import com.cdac.elearning.dto.Status;
import com.cdac.elearning.dto.Status.StatusType;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Quiz;
import com.cdac.elearning.service.QuizService;

@RestController
@CrossOrigin
public class quizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("/quiz/add")
	public Status addQuiz(@RequestBody List<Quiz> quiz) {
		try {
			//System.out.println(quiz.getQuestion());
			quizService.addQuiz(quiz);
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
	
	@PostMapping("/quiz/update")
	public Status updateQuiz(@RequestBody Quiz quiz) {
		try {
			//System.out.println(quiz.getQuestion());
			quizService.updateQuiz(quiz);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Quiz Successfully Updated!!");
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
	
	
	@PostMapping("/quiz/delete")
	public Status deleteQuiz(@RequestBody Quiz quiz) {
		try {
			//System.out.println(quiz.getQuestion());
			quizService.deleteQuiz(quiz);
			Status status=new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Quiz Successfully deleted!!");
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
	
	
	@GetMapping("/getquiz")
	public List<QuizResponse> getQuiz(@RequestParam("courseName") String courseName) {
		try {
			//System.out.println(quiz.getQuestion());
			
			return quizService.getQuiz(courseName); 
			
		}		
		catch(CourseException e) {
			List<QuizResponse> list= new ArrayList<QuizResponse>();
			return list;
		}
		
	}
}
