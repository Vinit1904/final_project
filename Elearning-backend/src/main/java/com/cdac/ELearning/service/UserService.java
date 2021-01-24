package com.cdac.ELearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdac.ELearning.dto.AuthenticationResponse;
import com.cdac.ELearning.dto.Login;
import com.cdac.ELearning.model.Course;
import com.cdac.ELearning.model.Quiz;
import com.cdac.ELearning.model.Score;
import com.cdac.ELearning.model.User;

@Service
public interface UserService{

	void createUser(User user);
	AuthenticationResponse login(Login loginRequest);
	void updateResetPasswordToken(String token, String email);
	User getByResetPasswordToken(String token);
	void updatePassword(User user, String newPassword);
    User fetchUserByEmailId(String id);
    List<User> fetchAllUsers();
    List<Course> enrolledCourses(String id);
    List<Quiz> fetchQuiz(String name);
    void addCourse(String emailId,String courseName);
    Score getScore(String emailId,String courseName);
    
}
