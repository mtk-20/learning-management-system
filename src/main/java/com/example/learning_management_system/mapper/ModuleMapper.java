package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.ModuleDto;
import com.example.learning_management_system.model.Module;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, ProgressMapper.class})
public interface ModuleMapper {

    ModuleDto toModuleDto(Module module);
    Module toModuleEntity(ModuleDto moduleDto);
}
