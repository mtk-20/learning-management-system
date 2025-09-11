package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.CourseMapper;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Module;
import com.example.learning_management_system.model.Student;
import com.example.learning_management_system.model.Teacher;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import com.example.learning_management_system.repository.StudentRepository;
import com.example.learning_management_system.repository.TeacherRepository;
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
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;
    private final CourseMapper courseMapper;

    // C
    public ResponseEntity<CourseDto> createCourse(CourseDto courseDto) {
        Course course = courseMapper.toCourseEntity(courseDto);
        if (courseDto.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(courseDto.getTeacherId()).orElseThrow(() -> new IdNotFoundException("No teacher id " + courseDto.getTeacherId()));
            course.setTeacher(teacher);
        }
        if (courseDto.getStudentIds() != null && !courseDto.getStudentIds().isEmpty()) {
            Set<Student> students = new HashSet<>(studentRepository.findAllById(courseDto.getStudentIds()));
            course.setStudents(students);
        }
        if (courseDto.getModuleIds() != null && !courseDto.getModuleIds().isEmpty()) {
            Set<Module> modules = new HashSet<>(moduleRepository.findAllById(courseDto.getModuleIds()));
            course.setModules(modules);
        }
        CourseDto newCourse = courseMapper.toCourseDto(courseRepository.save(course));
        return ResponseEntity.ok(newCourse);
    }

    // R
    public CourseDto getCourseById(long id) {
        return courseRepository.findById(id).map(courseMapper::toCourseDto).orElseThrow(() -> new IdNotFoundException("NO Course ID " + id));
    }

    public List<CourseDto> getAllCourse() {
        return courseRepository.findAll().stream().map(courseMapper::toCourseDto).collect(Collectors.toList());
    }

    // U
    public ResponseEntity<CourseDto> updateCourse(long id, CourseDto courseDto) {
        Course update = courseRepository.findById(id).orElseThrow(() -> new IdNotFoundException("NO SUCH ID " + id));
        update.setTitle(courseDto.getTitle());
        update.setDescription(courseDto.getDescription());
        CourseDto updatedCourse = courseMapper.toCourseDto(courseRepository.save(update));
        return ResponseEntity.ok(updatedCourse);
    }

    // D
    public ResponseEntity<String> deleteCourseById(long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.ok("Course id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO Course ID " + id);
        }
    }

}
