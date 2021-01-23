package com.cdac.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cdac.elearning.dto.ProblemResponse;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Problems;
import com.cdac.elearning.model.Quiz;
import com.cdac.elearning.model.Scores;
import com.cdac.elearning.model.TestCases;
import com.cdac.elearning.repository.CourseRepository;

@Service
public class ProblemService {

	
	@Autowired
	CourseRepository courseRepository;
	
	public List<ProblemResponse> findAllProblem(Problems problem)
	{
		try {	
		Course course=courseRepository.findByName(problem.getCourseName());
		List<Problems> list =course.getProblems();
		List<ProblemResponse> processList= new ArrayList<ProblemResponse>();
		
		for(Problems p:list)
		{
			ProblemResponse resObj=new ProblemResponse();
			resObj.setProblemName(p.getProblemName());
			resObj.setDescription(p.getDescription());
			resObj.setDifficulty(p.getDifficulty());
			processList.add(resObj);
		}
		
		 return processList;
		}
		catch(CourseException e) {
			throw new CourseException(e.getMessage());
		}
								
		
	}
	
	public ProblemResponse findOneProblem(Problems problem)
	{
		try {	
		Course course=courseRepository.findByName(problem.getCourseName());
		List<Problems> list =course.getProblems();
		List<ProblemResponse> processList= new ArrayList<ProblemResponse>();
		ProblemResponse resObj=new ProblemResponse();
		String s1=problem.getProblemName();
		for(Problems p:list)
		{
			String s2 =p.getProblemName();
			if(s1.equals(s2)) {
			resObj=new ProblemResponse();
			resObj.setProblemName(p.getProblemName());
			resObj.setDescription(p.getDescription());
			resObj.setDifficulty(p.getDifficulty());
			
			}
		}
		
		 return resObj;
		}
		catch(CourseException e) {
			throw new CourseException(e.getMessage());
		}
								
		
	}
	
	
	
	public void addProblem(Problems problem)
	{
		try {	
		Course course=courseRepository.findByName(problem.getCourseName());
		List<Problems> mproblem =course.getProblems();
		mproblem.add(problem);		
		course.setProblems(mproblem);
		
		courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new CourseException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Duplicate Problem Name");
		}						
		
	}
	
	public void updateProblem(Problems problem)
	{
		try {	
		Course course=courseRepository.findByName(problem.getCourseName());
		List<Problems> list =course.getProblems();
		
		String s1=problem.getProblemName();
		
		for(Problems p:list)
		{
			String s2=p.getProblemName();
			if(s1.equals(s2))
			{
				p.setDescription(problem.getDescription());
				p.setDifficulty(problem.getDifficulty());
			}
		}
		
		course.setProblems(list);
		
		courseRepository.save(course);
		}
		catch(CourseException e){
			throw new CourseException("Problem update fails");
		}						
		
	}
	
	public void deleteProblem(Problems problem)
	{
		try {	
		Course course=courseRepository.findByName(problem.getCourseName());
		List<Problems> list =course.getProblems();
		
		String s1=problem.getProblemName();
		
		for(Problems p:list)
		{
			String s2=p.getProblemName();
			if(s1.equals(s2))
			{
				list.remove(p);
			}
		}
				
		course.setProblems(list);
		
		courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new DuplicateKeyException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Duplicate Course Name");
		}						
		
	}
}
