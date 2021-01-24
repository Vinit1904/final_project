package com.cdac.ELearning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.ELearning.model.Score;
import com.cdac.ELearning.model.User;
import com.cdac.ELearning.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return this.userService.fetchAllUsers();
    }
    
    @GetMapping("/addCourse")
    public void addCourse(@PathVariable String emailId,@PathVariable String courseName){
        this.userService.addCourse(emailId,courseName);
    }
    
    @GetMapping("/getScore")
    public Score getCourse(@PathVariable String emailId,@PathVariable String courseName){
       return this.userService.getScore(emailId,courseName);
    }

}
