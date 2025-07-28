package com.project.ensitech.service.common;



import com.project.ensitech.model.dto.AdministratorDto;

import java.util.List;

public interface IAdministratorService {
    AdministratorDto createAdministrator(AdministratorDto dto);
    List<AdministratorDto> getAllAdministrators();
    AdministratorDto getAdministratorById(Long id);
    AdministratorDto updateAdministrator(Long id, AdministratorDto dto);
    void deleteAdministrator(Long id);
}
