package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.AdministratorDto;
import com.project.ensitech.model.entity.Administrator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    AdministratorDto toDto(Administrator administrator);
    Administrator toEntity(AdministratorDto administratorDto);
}
