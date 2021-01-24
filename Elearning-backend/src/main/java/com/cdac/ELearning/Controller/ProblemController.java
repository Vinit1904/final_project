package com.cdac.ELearning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cdac.ELearning.model.Problem;
import com.cdac.ELearning.model.Score;
import com.cdac.ELearning.service.CourseService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    CourseService courseService;

    @PostMapping("/add/{courseName}")
    public String addProblem(@RequestBody Problem problem, @PathVariable String courseName){

        this.courseService.addProblem(problem,courseName);
        
        return "Success";
    }

    @GetMapping("/all/{name}")
    public List<Problem> fetchAllProblems(@PathVariable String name){

        return this.courseService.fetchAllProblemsByCourse(name);
    }

    
    @PostMapping("/score/{emailId}/{courseName}")
    public void addQuizScore(@PathVariable String emailId,@PathVariable String courseName,@RequestBody Score score){

        courseService.addQuizScore(emailId,courseName,score);
    }
    
    @GetMapping("/one/{course}/{name}")
    public Problem fetchProblem(@PathVariable String course,@PathVariable String name){

        return this.courseService.fetchProblem(course,name);
    }
}

