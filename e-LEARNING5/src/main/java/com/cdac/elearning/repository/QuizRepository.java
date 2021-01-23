package com.cdac.elearning.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Quiz;

public interface QuizRepository extends MongoRepository<Quiz,String> {

	@Query("{'name' : ?0}")
	public Quiz findByName(String name);
	
	@Query("{'name' : ?0}")
	public List<Quiz> getQuizByName(String name);
}
