package com.cdac.ELearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdac.ELearning.dto.AuthenticationResponse;
import com.cdac.ELearning.dto.Login;
import com.cdac.ELearning.model.Course;
import com.cdac.ELearning.model.Quiz;
import com.cdac.ELearning.model.Score;
import com.cdac.ELearning.model.User;
import com.cdac.ELearning.repository.CourseRepository;
import com.cdac.ELearning.repository.UserRepository;
import com.cdac.ELearning.security.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

	 @Autowired
	  private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 
	 @Autowired
	 private JwtProvider jwtProvider;
	
	@Override
	public void createUser(User user)
	{
		 User isUserPresent = userRepository.findByEmail(user.getEmailId());
		 if(isUserPresent == null) {
			 userRepository.save(user);
		 }
		 else
			 throw new DuplicateKeyException("User Already Present");
	}
	
	
	@Override
	 public AuthenticationResponse login(Login loginRequest) throws InvalidKeyException {
	       
		 try {
		 Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(),
	                loginRequest.getPassword()));
	        System.out.print(authenticate);
	       SecurityContextHolder.getContext().setAuthentication(authenticate);
	        String authenticationToken = jwtProvider.generateToken(authenticate);
	       //String authenticationToken="dd";
	        User userDeatails = userRepository.findByEmail(loginRequest.getEmailId());
	        
	        return new AuthenticationResponse(authenticationToken,userDeatails.getEmailId(),userDeatails.getFirstName());
		 }
		 catch(UsernameNotFoundException e) {
			 throw new UsernameNotFoundException("User not Found");
		 }
		 catch(BadCredentialsException e) {
			 throw new BadCredentialsException("Bad Credentials");
		 }
	  }
	
	
	 @Override
	 public void updateResetPasswordToken(String token, String email)  {
	        User user = userRepository.findByEmail(email);
	        if (user != null) {
	            user.setReset_password_token(token);
	            userRepository.save(user);
	        }
	    }
	     
	 
	   @Override
	    public User getByResetPasswordToken(String token) {
	        return userRepository.findByResetPasswordToken(token);
	    }
	   
	    @Override
	    public void updatePassword(User user, String newPassword) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedPassword);
	         
	        user.setReset_password_token(null);
	        userRepository.save(user);
	    }
	
    
    
	 @Override
	 public void addCourse(String emailId,String courseName) {
		    User user= this.userRepository.findById(emailId).get();
		    List<Course> list =user.getCourse();
	        String s1=courseName;
	        for(Course c :list)
	        {
	        	String s2=c.getCourseName();
	        	if(s1.equals(s2))
	        	{
	        		return ;
	        	}
	        	else
	        	{
	        		Course course=this.courseRepository.findByCourseName(courseName);
	        		list.add(course);

	    	        user.setCourse(list);
	    	        userRepository.save(user);
	        	}
	        }
	  }
	 
	 @Override
	 public Score getScore(String emailId,String courseName) {
		    User user= this.userRepository.findById(emailId).get();
	        List<Course> list =user.getCourse();
		    Score score =new Score();
	        String s1=courseName;
	        for(Course c :list)
	        {
	        	String s2=c.getCourseName();
	        	if(s1.equals(s2))
	        	{
	        		score=c.getScore();
	        	}
	        	
	        }
	      return score;
	  }
    
    @Override
    public User fetchUserByEmailId(String id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> fetchAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<Course> enrolledCourses(String id) {

        User user = this.userRepository.findById(id).get();
        return user.getCourse();
    }

    @Override
    public List<Quiz> fetchQuiz(String name) {

       Course course = this.courseRepository.findByCourseName(name);
        return course.getQuiz();
    }
	

}