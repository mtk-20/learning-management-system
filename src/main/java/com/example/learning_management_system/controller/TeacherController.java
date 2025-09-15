package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.TeacherDto;
import com.example.learning_management_system.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<TeacherDto> createTeachers(@RequestBody TeacherDto teacherDto) {
        return teacherService.createTeacher(teacherDto);
    }

    @GetMapping
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeachersById(@PathVariable("id") long id) {
        return teacherService.getTeacherById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherDto> updateTeachers(@PathVariable("id") long id, @RequestBody TeacherDto teacherDto) {
        return teacherService.updateTeacher(id, teacherDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeachers(@PathVariable("id") long id) {
        return teacherService.deleteTeacher(id);
    }
}
