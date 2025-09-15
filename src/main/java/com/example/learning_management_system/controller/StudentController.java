package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.StudentDto;
import com.example.learning_management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudents(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public StudentDto gtStudentsById(@PathVariable("id") long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudents(@PathVariable("id") long id, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudents(@PathVariable("id") long id) {
        return studentService.deleteStudent(id);
    }
}
