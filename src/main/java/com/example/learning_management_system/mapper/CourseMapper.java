package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Module;
import com.example.learning_management_system.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "students", target = "studentIds")
    @Mapping(source = "modules", target = "moduleIds")
    CourseDto toCourseDto(Course course);

    @Mapping(source = "teacherId", target = "teacher.id")
    Course toCourseEntity(CourseDto courseDto);

    default Set<Long> mapStudents(Set<Student> students) {
        if (students == null) return Collections.emptySet();
        return students.stream().map(Student::getId).collect(Collectors.toSet());
    }

    default Set<Long> mapModules(Set<Module> modules) {
        if (modules == null) return Collections.emptySet();
        return modules.stream().map(Module::getId).collect(Collectors.toSet());
    }
}
