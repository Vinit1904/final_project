package com.cdac.ELearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cdac.ELearning.model.Course;


@Repository
public interface CourseRepository extends MongoRepository<Course,String> {

    Course findByCourseName(String name);

}