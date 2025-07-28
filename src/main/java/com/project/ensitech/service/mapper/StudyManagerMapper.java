package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.StudyManagerDto;
import com.project.ensitech.model.entity.StudyManager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")//Indique à MapStruct de générer un bean Spring
public interface StudyManagerMapper {
    StudyManagerDto toDto(StudyManager studyManager);
    StudyManager toEntity(StudyManagerDto studyManagerDto);

}
