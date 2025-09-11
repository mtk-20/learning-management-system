package com.example.learning_management_system.controller;

import com.example.learning_management_system.dto.ModuleDto;
import com.example.learning_management_system.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/module")
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping("/create")
    public ResponseEntity<ModuleDto> createModules(@RequestBody ModuleDto moduleDto) {
        return moduleService.createModule(moduleDto);
    }

    @GetMapping
    public List<ModuleDto> getAllModules() {
        return moduleService.getAllModule();
    }

    @GetMapping("/{id}")
    public ModuleDto getModuleById(@PathVariable("id") long id) {
        return moduleService.getModuleById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ModuleDto> updateModules(@PathVariable("id") long id, @RequestBody ModuleDto moduleDto) {
        return moduleService.updateModule(id, moduleDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteModules(@PathVariable("id") long id) {
        return moduleService.deleteCourse(id);
    }
}
