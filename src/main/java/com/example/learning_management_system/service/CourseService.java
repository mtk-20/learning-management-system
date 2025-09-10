package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.CourseMapper;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public ResponseEntity<CourseDto> createCourse(CourseDto courseDto) {
        Course course = courseMapper.toCourseEntity(courseDto);
        CourseDto newCourse = courseMapper.toCourseDto(courseRepository.save(course));
        return ResponseEntity.ok(newCourse);
    }

    public CourseDto getCourseById(long id) {
        return courseRepository.findById(id).map(courseMapper::toCourseDto).orElseThrow(() -> new IdNotFoundException("NO SUCH ID " + id));
    }

    public List<CourseDto> getAllCourse() {
        return courseRepository.findAll().stream().map(courseMapper::toCourseDto).collect(Collectors.toList());
    }

    public ResponseEntity<CourseDto> updateCourse(long id, CourseDto courseDto) {
        Course update = courseRepository.findById(id).orElseThrow(() -> new IdNotFoundException("NO SUCH ID " + id));
        update.setTitle(courseDto.getTitle());
        update.setDescription(courseDto.getDescription());
        CourseDto updatedCourse = courseMapper.toCourseDto(courseRepository.save(update));
        return ResponseEntity.ok(updatedCourse);
    }

    public ResponseEntity<String> deleteCourseById(long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok("Course id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course id " + id + " not found.");
        }
    }

}
