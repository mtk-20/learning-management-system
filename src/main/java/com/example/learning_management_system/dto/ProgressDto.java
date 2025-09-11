package com.example.learning_management_system.dto;

import com.example.learning_management_system.model.Progress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressDto {

    private Long id;
    private String status;
    private LocalDate completionDate;
    private Long studentId;
    private Long moduleId;
}
