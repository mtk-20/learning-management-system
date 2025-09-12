package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.ProgressDto;
import com.example.learning_management_system.model.Progress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ModuleMapper.class, StudentMapper.class})
public interface ProgressMapper {

    ProgressDto toProgressDto(Progress progress);

    Progress toProgressEntity(ProgressDto progressDto);
}
