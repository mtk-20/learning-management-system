package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.TeacherDto;
import com.example.learning_management_system.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface TeacherMapper {

    TeacherDto toTeacherDto(Teacher teacher);
    Teacher toTeacherEntity(TeacherDto teacherDto);
}
