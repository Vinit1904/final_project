package com.cdac.elearning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.cdac.elearning.dto.Question;
import com.cdac.elearning.dto.QuizResponse;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Quiz;
import com.cdac.elearning.repository.CourseRepository;
import com.cdac.elearning.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	CourseRepository courseRepository; 
	
	public void addQuiz(List<Quiz> quiz)
	{
		try {
			
			//Course course=courseRepository.findByName(quiz.getCourseName());///loasdd
			List<Quiz> mquiz =course.getQuiz();			
			mquiz.addAll(quiz);
			course.setQuiz(mquiz);
			courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new CourseException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Duplicate quiz Name");
		}						
		
	}
	
	
	
	public void updateQuiz(Quiz question)
	{
		try {
			
			Course course=courseRepository.findByName(question.getCourseName());
			
			List<Quiz> list =course.getQuiz();
			String s1=question.getQuestion();
			for(Quiz q :list)
			{
				String s2=q.getQuestion();
				if(s1.equals(s2))
				{
					q.setCorrect_answer(question.getCorrect_answer());
					q.setIncorrect_answers(question.getIncorrect_answers());
				}
			}
			course.setQuiz(list);
			courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new CourseException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Duplicate Quiz Name");
		}						
		
	}
	
	
	
	public void deleteQuiz(Quiz question)
	{
		try {
			
			Course course=courseRepository.findByName(question.getCourseName());
			
			List<Quiz> list =course.getQuiz();
			String s1=question.getQuestion();
			for(Quiz q :list)
			{
				String s2=q.getQuestion();
				if(s1.equals(s2))
				{
					list.remove(q);
				}
			}
			course.setQuiz(list);
			courseRepository.save(course);
		}
		catch(DuplicateKeyException e) {
			throw new CourseException(e.getMessage());
		}
		catch(CourseException e){
			throw new CourseException("Quiz not able to delete");
		}						
		
	}
	
	
	
	public List<QuizResponse> getQuiz(String name)
	{
		try {
			Course course=courseRepository.findByName(name);
			List<Quiz> list =course.getQuiz();
			List<QuizResponse> processQuiz = new ArrayList<QuizResponse>();
			
			for(Quiz q : list)
			{
				QuizResponse resObj =new QuizResponse();
				resObj.setCorrect_answer(q.getCorrect_answer());
				resObj.setIncorrect_answers(q.getIncorrect_answers());
				resObj.setQuestion(q.getQuestion());
				processQuiz.add(resObj);
			}
						
			return processQuiz;
		}
		catch(CourseException e){
			throw new CourseException("Quiz Unable to Fetch");
		}						
		
	}
}
