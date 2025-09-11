package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class, ModuleMapper.class})
public interface CourseMapper {

    CourseDto toCourseDto(Course course);
    Course toCourseEntity(CourseDto courseDto);
}
