package com.example.learning_management_system.mapper;

import com.example.learning_management_system.dto.ProgressDto;
import com.example.learning_management_system.model.Progress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mapping(source = "module.id", target = "moduleId")
    @Mapping(source = "student.id", target = "studentId")
    ProgressDto toProgressDto(Progress progress);

    //    @Mapping(source = "moduleId", target = "module.id")
    //    @Mapping(source = "studentId", target = "moduleId")
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "module", ignore = true)
    Progress toProgressEntity(ProgressDto progressDto);
}
