package com.cdac.elearning.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cdac.elearning.model.User;

public interface userRepository extends MongoRepository<User,ObjectId> {
	
	
	
	@Query("{'emailId' : ?0}")
	public User findByEmail(String email);
	
	@Query("{'reset_password_token' : ?0}")
	public User findByResetPasswordToken(String token);
	
	
}
