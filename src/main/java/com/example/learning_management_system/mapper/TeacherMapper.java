package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.TeacherDto;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Teacher;
import org.mapstruct.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface TeacherMapper {

    @Mapping(source = "courses", target = "courseIds")
    TeacherDto toTeacherDto(Teacher teacher);

    @Mapping(target = "courses", ignore = true)
    Teacher toTeacherEntity(TeacherDto teacherDto);

    default Set<Long> mapCourses(Set<Course> courses) {
        if (courses == null) return new HashSet<>();
        return courses.stream().map(Course::getId).collect(Collectors.toSet());
    }
}
