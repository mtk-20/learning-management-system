package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.StudentDto;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface StudentMapper {

    @Mapping(source = "enrolledCourses", target = "enrolledCourseIds")
    StudentDto toStudentDto(Student student);

    //@Mapping(source = "enrolledCourseIds", target = "enrolledCourses")
    @Mapping(target = "enrolledCourses", ignore = true)
    Student toStudentEntity(StudentDto studentDto);

    default Set<Long> mapCourses(Set<Course> courses) {
        if (courses == null) return new HashSet<>();
        return courses.stream().map(Course::getId).collect(Collectors.toSet());
    }
}

