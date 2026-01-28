package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.DirectorDto;
import com.project.ensitech.model.entity.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")//indique a MapStruct de créer un bean spring
public interface DirectorMapper {
    DirectorDto toDto(Director director);
    Director toEntity(DirectorDto directorDto);
}
