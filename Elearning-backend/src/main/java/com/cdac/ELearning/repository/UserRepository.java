package com.cdac.ELearning.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdac.ELearning.model.User;


@Repository
public interface UserRepository extends MongoRepository<User,String>{

    User findByEmailIdAndPassword(String emailId,String password);
    
    @Query("{'emailId' : ?0}")
	public User findByEmail(String email);
	
    @Query("{'reset_password_token' : ?0}")
	public User findByResetPasswordToken(String token);
	
}