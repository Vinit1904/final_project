package com.cdac.elearning.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cdac.elearning.model.Course;
import com.cdac.elearning.model.Quiz;

public interface CourseRepository extends MongoRepository<Course,ObjectId> {

	@Query("{'CourseName' : ?0}")
	public Course findByName(String name);
	
	@Query("{'CourseName' : ?0}")
	public List<Quiz> findQuizByName(String name);
	
	@Query("{courseName: 0, problems: {$elemMatch: {problemName: \"java problem 1\"}}}")
	public Course findByNameProblemName(String courseName,String problemName);
}
