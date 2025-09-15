package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.ProgressDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.ProgressMapper;
import com.example.learning_management_system.model.Module;
import com.example.learning_management_system.model.Progress;
import com.example.learning_management_system.model.Student;
import com.example.learning_management_system.repository.ModuleRepository;
import com.example.learning_management_system.repository.ProgressRepository;
import com.example.learning_management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final StudentRepository studentRepository;
    private final ModuleRepository moduleRepository;
    private final ProgressMapper progressMapper;

    // C
    public ResponseEntity<ProgressDto> createProgress(ProgressDto progressDto) {
        Progress progress = progressMapper.toProgressEntity(progressDto);
        if (progressDto.getStudentId() != null) {
            Student student = studentRepository.findById(progressDto.getStudentId()).orElseThrow(() -> new IdNotFoundException("No Student ID " + progressDto.getStudentId()));
            progress.setStudent(student);
        }
        if (progressDto.getModuleId() != null) {
            Module module = moduleRepository.findById(progressDto.getModuleId()).orElseThrow(() -> new IdNotFoundException("No Module ID " + progressDto.getModuleId()));
            progress.setModule(module);
        }
        ProgressDto newProgress = progressMapper.toProgressDto(progressRepository.save(progress));
        return ResponseEntity.ok(newProgress);
    }

    // R
    public List<ProgressDto> getAllModule() {
        return progressRepository.findAll().stream().map(progressMapper::toProgressDto).collect(Collectors.toList());
    }

    public ProgressDto getModuleById(long id) {
        return progressRepository.findById(id).map(progressMapper::toProgressDto).orElseThrow(() -> new IdNotFoundException("No Student ID " + id));
    }

    // U
    public ResponseEntity<ProgressDto> updateProgress(long id, ProgressDto progressDto) {
        Progress update = progressRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No Student ID " + progressDto.getId()));
        update.setStatus(progressDto.getStatus());
        update.setCompletionDate(progressDto.getCompletionDate());
        if (progressDto.getStudentId() != null) {
            Student student = studentRepository.findById(progressDto.getStudentId()).orElseThrow(() -> new IdNotFoundException("No Student ID " + progressDto.getStudentId()));
            update.setStudent(student);
        }
        if (progressDto.getModuleId() != null) {
            Module module = moduleRepository.findById(progressDto.getModuleId()).orElseThrow(() -> new IdNotFoundException("No Module ID " + progressDto.getModuleId()));
            update.setModule(module);
        }
        ProgressDto updatedProgress = progressMapper.toProgressDto(progressRepository.save(update));
        return ResponseEntity.ok(updatedProgress);
    }

    // D
    public ResponseEntity<String> deleteProgress(long id) {
        if (progressRepository.existsById(id)) {
            progressRepository.deleteById(id);
            return ResponseEntity.ok("Progress id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Progress ID " + id);
        }
    }

}


