package com.cdac.ELearning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdac.ELearning.model.Quiz;
import com.cdac.ELearning.model.Score;
import com.cdac.ELearning.service.CourseService;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
   CourseService courseService;

    @PostMapping("/add/{name}")
    public void addQuiz(@RequestBody List<Quiz> quiz, @PathVariable String name){

        courseService.addQuiz(quiz,name);
    }

    @PostMapping("/score/{emailId}/{courseName}")
    public void addQuizScore(@PathVariable String emailId,@PathVariable String courseName,@RequestBody Score score){

        courseService.addQuizScore(emailId,courseName,score);
    }
    @GetMapping("/get/{name}")
    public List<Quiz> getQuiz(@PathVariable String name){
        return courseService.getQuiz(name);
    }

}
