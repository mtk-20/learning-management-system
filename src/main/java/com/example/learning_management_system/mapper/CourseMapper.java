package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.CourseDto;
import com.example.learning_management_system.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper Ins = Mappers.getMapper(CourseMapper.class);

    CourseDto toCourseDto(Course course);
    Course toCourseEntity(CourseDto courseDto);
}
