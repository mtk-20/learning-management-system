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
public class ModuleDto {

    private Long id;
    private String title;
    private String content;
    private Long courseId;
}
