package com.cdac.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cdac.elearning.dto.Score;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Problems;
import com.cdac.elearning.model.Scores;
import com.cdac.elearning.model.TestCases;
import com.cdac.elearning.repository.CourseRepository;

@Service
public class TestCasesService {

	@Autowired
	CourseRepository courseRepository;
	

	public void addTestCases(TestCases testCases)
	{
				
		Course courses = courseRepository.findByName(testCases.getCourseName());		
		
		List<Problems> list =courses.getProblems();
		
		String s1=testCases.getProblemName();
		for(Problems p:list)
		{
			String s2=p.getProblemName();
			
			if(s1.equals(s2)) {
				System.out.println();
				System.out.println(p.getProblemName());
				List<TestCases> ts =p.getTestCases();
				ts.add(testCases);
				p.setTestCases(ts);
			}
		}
		courses.setProblems(list);
		courseRepository.save(courses);
		
	}
	
	
	
	
	
}
