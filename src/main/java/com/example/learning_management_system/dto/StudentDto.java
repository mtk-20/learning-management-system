package com.example.learning_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<CourseDto> sCourses;
}
