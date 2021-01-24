package com.cdac.ELearning.Controller;


import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.ELearning.config.EmailCfg;
import com.cdac.ELearning.dto.AuthenticationResponse;
import com.cdac.ELearning.dto.Login;
import com.cdac.ELearning.dto.LoginResponse;
import com.cdac.ELearning.dto.Status;
import com.cdac.ELearning.dto.Status.StatusType;
import com.cdac.ELearning.model.User;
import com.cdac.ELearning.repository.UserRepository;
import com.cdac.ELearning.service.UserService;

import io.jsonwebtoken.security.InvalidKeyException;
import net.bytebuddy.utility.RandomString;

@RestController
@CrossOrigin
public class AuthController {

	
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
   

	@Autowired
	private MailSender mailSender;
	
    @Autowired
    private EmailCfg emailCfg;
    
    @Autowired
	private UserService userService;
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public Status createUser(@RequestBody User user)
	{	
		try {	
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setRole("role_user");
		
			userService.createUser(user);;
        
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
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody Login loginRequest) throws InvalidKeyException {
		try {
			AuthenticationResponse authResponse=userService.login(loginRequest);
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

	    
        	userService.updateResetPasswordToken(token, emailId);
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
	       
	      User users = userService.getByResetPasswordToken(token);
	      
	      if (users == null) {
	    	  	Status res= new Status();
		        res.setStatus(StatusType.FAILURE);
		        res.setMessage("unauthorized User");
		        return res; 
	      } else {           
	    	  userService.updatePassword(users, password);
	          	Status res= new Status();
		        res.setStatus(StatusType.FAILURE);
		        res.setMessage("Password Upadte Successfull");
		        return res;
	      }
	       
	  }
    
    
}
