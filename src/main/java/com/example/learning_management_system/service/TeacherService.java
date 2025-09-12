package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.TeacherDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.TeacherMapper;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Teacher;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.TeacherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final TeacherMapper teacherMapper;

    // C
    public ResponseEntity<TeacherDto> createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toTeacherEntity(teacherDto);
        if (teacherDto.getCourseIds() != null && !teacherDto.getCourseIds().isEmpty()) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(teacherDto.getCourseIds()));
            teacher.setCourses(courses);
            courses.forEach(course -> course.setTeacher(teacher));
        }

        TeacherDto newTeacher = teacherMapper.toTeacherDto(teacherRepository.save(teacher));
        return ResponseEntity.ok(newTeacher);
    }

    // R
    public List<TeacherDto> getAllTeacher() {
        return teacherRepository.findAll().stream().map(teacherMapper::toTeacherDto).collect(Collectors.toList());
    }

    public TeacherDto getTeacherById(long id) {
        return teacherRepository.findById(id).map(teacherMapper::toTeacherDto).orElseThrow(() -> new IdNotFoundException("No Teacher ID " + id));
    }

    // U
    public ResponseEntity<TeacherDto> updateTeacher(long id, TeacherDto teacherDto) {
        Teacher update = teacherRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No Teacher ID " + id));
        update.setName(teacherDto.getName());
        update.setEmail(teacherDto.getEmail());
        // optional for update teacher.courseId
        if (teacherDto.getCourseIds() != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(teacherDto.getCourseIds()));
            update.setCourses(courses);
            courses.forEach(c -> c.setTeacher(update));
        }
        TeacherDto updatedTeacher = teacherMapper.toTeacherDto(teacherRepository.save(update));
        return ResponseEntity.ok(updatedTeacher);
    }

    // D
    public ResponseEntity<String> deleteTeacher(long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return ResponseEntity.ok("Teacher id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Teacher ID " + id);
        }
    }
}
