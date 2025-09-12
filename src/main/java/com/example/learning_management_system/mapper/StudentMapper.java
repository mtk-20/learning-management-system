package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.StudentDto;
import com.example.learning_management_system.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper {

    StudentDto toStudentDto(Student student);

    Student toStudentEntity(StudentDto studentDto);
}

