package com.cdac.elearning.controller;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cdac.elearning.service.userService;

import io.jsonwebtoken.security.InvalidKeyException;
import net.bytebuddy.utility.RandomString;

import com.cdac.elearning.config.EmailCfg;
import com.cdac.elearning.dto.AuthenticationResponse;
import com.cdac.elearning.dto.Login;
import com.cdac.elearning.dto.LoginResponse;
import com.cdac.elearning.dto.Score;
import com.cdac.elearning.dto.ScoreResponse;
import com.cdac.elearning.dto.Status;
import com.cdac.elearning.dto.Status.StatusType;
import com.cdac.elearning.exception.CourseException;
import com.cdac.elearning.exception.mailException;
import com.cdac.elearning.model.User;;


@RestController
@CrossOrigin
public class userController {

	@Autowired
	private userService userServ;
	
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
   

	@Autowired
	private MailSender mailSender;
	
    @Autowired
    private EmailCfg emailCfg;
    
    
	@RequestMapping("/welcome")
	public String User()
	{		
		System.out.print("ss");
		return "hello";		
	}
	
	@PostMapping("/create_user")
	public Status createUser(@RequestBody User user)
	{	
		try {	
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setRole("role_user");
		
			userServ.createUser(user);
        
			Status res= new Status();
			res.setStatus(StatusType.SUCCESS);
			res.setMessage("Registration Successful!");
			return res;
		}
		catch(DuplicateKeyException e){
			Status res= new Status();
	        res.setStatus(StatusType.FAILURE);
	        res.setMessage("Registration unsuccessful!");
	        return res;
	}
       
	}
	
	
	@PostMapping("/logina")
    public LoginResponse login(@RequestBody Login loginRequest) throws InvalidKeyException {
		try {
			AuthenticationResponse authResponse=userServ.login(loginRequest);
			LoginResponse res =new LoginResponse();
			res.setStatus(StatusType.SUCCESS);
			res.setMessage("Login SucessFull");
			res.setAuthToken(authResponse.getAuthenticationToken());			
			res.setName(authResponse.getFirstName());
			res.setEmailId(authResponse.getUsername());
			return res;
		}
		catch(UsernameNotFoundException e){
			
			LoginResponse res =new LoginResponse();
			res.setStatus(StatusType.FAILURE);
			res.setMessage(e.getMessage());
			res.setAuthToken("");			
			res.setName("");
			return res;
		}
		catch(BadCredentialsException e){
			LoginResponse res =new LoginResponse();
			res.setStatus(StatusType.FAILURE);
			res.setMessage(e.getMessage());
			res.setAuthToken("");			
			res.setName("");
			return res;
		}
    }
	
	

	
	@GetMapping("/home")
    public String home() {
        
		return "home";
    }
	
	@GetMapping("/auth")
    public String auth() {
        
		return "auty";
    }
	
	@GetMapping("/admin")
    public String admin() {
        
		return "admin";
    }
	
	@GetMapping("/logout_success")
    public String logout_success() {
		
        return "logout2";
    }
	
	
	@PostMapping("/forgot_password")
    public Status processForgotPassword(@RequestBody User loginRequest) throws UnsupportedEncodingException, MessagingException {
		String emailId=loginRequest.getEmailId();
		String token = RandomString.make(30);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailCfg.getHost());
        mailSender.setPort(this.emailCfg.getPort());
        mailSender.setUsername(this.emailCfg.getUsername());
        mailSender.setPassword(this.emailCfg.getPassword());
       

        Properties mailProps = new Properties();
        mailProps.put("mail.smtps.auth", "true");
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.smtp.debug", "true");
        
        mailSender.setJavaMailProperties(mailProps);

	    
	       userServ.updateResetPasswordToken(token, emailId);
	       String resetPasswordLink = "http://localhost:3000/ForgotPass?token=" + token;
	          
	      MimeMessage message = mailSender.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(message);
			  
			  helper.setFrom("vinitsarode86@gmail.com", "E-learning Support");
			  helper.setTo(emailId);
			  
			  String subject = "Here's the link to reset your password";
			  
			  String content = "<p>Hello,</p>" +
			  "<p>You have requested to reset your password.</p>" +
			  "<p>Click the link below to change your password:</p>" + "<p><a href=\"" +
			  resetPasswordLink + "\">Change my password</a></p>" + "<br>" +
			  "<p>Ignore this email if you do remember your password, " +
			  "or you have not made the request.</p>";
			  
			  helper.setSubject(subject);
			  
			  helper.setText(content, true);
			 
			  try {
			  mailSender.send(message);
			  	Status res= new Status();
		        res.setStatus(StatusType.SUCCESS);
		        res.setMessage("mail send to your emailId!");
			  
		        return res;
			  }
			  catch(Exception e)
			  {
				  Status res= new Status();
			        res.setStatus(StatusType.FAILURE);
			        res.setMessage("unable to send Fail!");
			        return res;  
			  }
			  
	
    }
     
	  
	  @PostMapping("/reset_password")
	  public Status processResetPassword(@RequestBody User user) {
	      String token = user.getReset_password_token();
	      String password = user.getPassword();
	       
	      User users = userServ.getByResetPasswordToken(token);
	      
	      if (users == null) {
	    	  	Status res= new Status();
		        res.setStatus(StatusType.FAILURE);
		        res.setMessage("unauthorized User");
		        return res; 
	      } else {           
	          userServ.updatePassword(users, password);
	          	Status res= new Status();
		        res.setStatus(StatusType.FAILURE);
		        res.setMessage("Password Upadte Successfull");
		        return res;
	      }
	       
	  }
	  
	
	  
	  @GetMapping("/addCoursetoCart")
	    public Status addCourse(@RequestParam("emailId")String emailId,@RequestParam("courseName")String courseName) {
			try {	
				
				userServ.addCourse(emailId,courseName);
	        
				Status res= new Status();
				res.setStatus(StatusType.SUCCESS);
				res.setMessage("Course Added Successful!");
				return res;
			}
			catch(CourseException e){
				Status res= new Status();
		        res.setStatus(StatusType.FAILURE);
		        res.setMessage("Course Added unsuccessful!");
		        return res;
		}
	  }
	  
	  @GetMapping("/getscores")
		public ScoreResponse getScores(@RequestParam("emailId")String emailId,@RequestParam("courseName") String name) {
			try {
				
				return userServ.getScore(emailId,name); 
				
			}
			
			catch(CourseException e) {
				ScoreResponse score =new ScoreResponse();
				return score;
			}
			
		}
    
}
