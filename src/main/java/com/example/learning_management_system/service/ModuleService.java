package com.example.learning_management_system.service;

import com.example.learning_management_system.dto.ModuleDto;
import com.example.learning_management_system.exception.IdNotFoundException;
import com.example.learning_management_system.mapper.ModuleMapper;
import com.example.learning_management_system.model.Course;
import com.example.learning_management_system.model.Module;
import com.example.learning_management_system.repository.CourseRepository;
import com.example.learning_management_system.repository.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final ModuleMapper moduleMapper;

    // C
    public ResponseEntity<ModuleDto> createModule(ModuleDto moduleDto) {
        Module module = moduleMapper.toModuleEntity(moduleDto);
        if (moduleDto.getCourseId() != null) {
            Course course = courseRepository.findById(moduleDto.getCourseId()).orElseThrow(() -> new IdNotFoundException("No Module ID " + moduleDto.getCourseId()));
            module.setCourse(course);
        }
        ModuleDto newModule = moduleMapper.toModuleDto(moduleRepository.save(module));
        return ResponseEntity.ok(newModule);
    }

    // R
    public ModuleDto getModuleById(long id) {
        return moduleRepository.findById(id).map(moduleMapper::toModuleDto).orElseThrow(() -> new IdNotFoundException("No Module ID " + id));
    }

    public List<ModuleDto> getAllModule() {
        return moduleRepository.findAll().stream().map(moduleMapper::toModuleDto).collect(Collectors.toList());
    }

    // U
    public ResponseEntity<ModuleDto> updateModule(long id, ModuleDto moduleDto) {
        Module update = moduleRepository.findById(id).orElseThrow(() -> new IdNotFoundException("No Module ID " + moduleDto.getId()));
        update.setTitle(moduleDto.getTitle());
        update.setContent(moduleDto.getContent());
        ModuleDto updatedModule = moduleMapper.toModuleDto(moduleRepository.save(update));
        return ResponseEntity.ok(updatedModule);
    }

    // D
    public ResponseEntity<String> deleteCourse(long id) {
        if (moduleRepository.existsById(id)) {
            moduleRepository.deleteById(id);
            return ResponseEntity.ok("Module id " + id + " deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Module ID " + id);
        }
    }

}
