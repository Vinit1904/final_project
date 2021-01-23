package com.cdac.elearning.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cdac.elearning.model.Quiz;

public interface ScoreRepository extends MongoRepository<Quiz,String> {

	
}



