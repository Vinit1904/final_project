package com.cdac.ELearning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.ELearning.model.Course;
import com.cdac.ELearning.service.CourseService;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public String addCourse(@RequestBody Course course){

        courseService.addCourse(course);
        return "Success";

    }

    @GetMapping("/get")
    public List<Course> fetchAllCourses(){

        return courseService.fetchAllCourses();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable String id){
        this.courseService.deleteCourse(id);
    }
}
