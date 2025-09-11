package com.example.learning_management_system.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private long id;

    @NotBlank(message = "Title require!")
    private String title;

    private String description;

    private TeacherDto teacher;
    private Set<StudentDto> students;
    private Set<ModuleDto> modules;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // for default message
    public void setDescription(String description) {
      if (description == null || description.trim().isEmpty()) {
          this.description = "No Description.";
      }
      else {
          this.description = description;
      }
    }
}
