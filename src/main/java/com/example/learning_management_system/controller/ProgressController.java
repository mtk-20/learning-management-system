package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.ProgressDto;
import com.example.learning_management_system.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/create")
    public ResponseEntity<ProgressDto> createProgresses(@RequestBody ProgressDto progressDto) {
        return progressService.createProgress(progressDto);
    }

    @GetMapping
    public List<ProgressDto> getAllProgresses() {
        return progressService.getAllModule();
    }

    @GetMapping("/{id}")
    public ProgressDto getProgressesById(@PathVariable("id") long id) {
        return progressService.getModuleById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProgressDto> updateProgresses(@PathVariable("id") long id, @RequestBody ProgressDto progressDto) {
        return progressService.updateProgress(id, progressDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProgresses(@PathVariable("id") long id) {
        return progressService.deleteProgress(id);
    }
}
