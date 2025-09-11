package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.StudentDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.StudentMapper;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Student;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.StudentRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    // C
    public ResponseEntity<StudentDto> createStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudentEntity(studentDto);
        if (studentDto.getEnrolledCourseIds() != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(studentDto.getEnrolledCourseIds()));
            student.setEnrolledCourses(courses);
        }
        StudentDto newStudent = studentMapper.toStudentDto(studentRepository.save(student));
        return ResponseEntity.ok(newStudent);
    }

    // R
    public List<StudentDto> getAllStudent() {
        return studentRepository.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    public StudentDto getStudentById(long id) {
        return studentRepository.findById(id).map(studentMapper::toStudentDto).orElseThrow(() -> new IdNotFoundException("No Student ID " + id));
    }

    // U
    public ResponseEntity<StudentDto> updateStudent(long id, StudentDto studentDto) {
        Student update = studentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No Student ID " + id));
        update.setFirstName(studentDto.getFirstName());
        update.setLastName(studentDto.getLastName());
        update.setEmail(studentDto.getEmail());
        update.setPassword(studentDto.getPassword());
        StudentDto updatedStudent = studentMapper.toStudentDto(studentRepository.save(update));
        return ResponseEntity.ok(updatedStudent);
    }

    // D
    public ResponseEntity<String> deleteStudent(long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Student ID " + id);
        }
    }

}
