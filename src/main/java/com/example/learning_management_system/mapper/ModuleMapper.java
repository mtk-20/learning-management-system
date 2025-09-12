package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.ModuleDto;
import com.example.learning_management_system.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    @Mapping(source = "course.id", target = "courseId")
    ModuleDto toModuleDto(Module module);

    @Mapping(source = "courseId", target = "course.id")
    Module toModuleEntity(ModuleDto moduleDto);
}
