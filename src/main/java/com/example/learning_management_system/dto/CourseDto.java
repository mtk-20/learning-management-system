package com.example.learning_management_system.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private long id;

    @NotBlank(message = "Title require!")
    private String title;

    private String description;

    private Long teacherId;
    private Set<Long> studentIds;
    private Set<Long> moduleIds;


    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            this.description = "No Description.";
        }
        else {
            this.description = description;
        }
    }
}
