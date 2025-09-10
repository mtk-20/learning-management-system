package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourses(@Valid @RequestBody CourseDto courseDto) {
        return courseService.createCourse(courseDto);
    }

    @GetMapping("/{id}")
    public CourseDto getCoursesById(@PathVariable("id") long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourse();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseDto> updateCourses(@PathVariable("id") long id, @Valid @RequestBody CourseDto courseDto) {
        return courseService.updateCourse(id, courseDto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCourses(@PathVariable("id") long id) {
        return courseService.deleteCourseById(id);
    }
}
