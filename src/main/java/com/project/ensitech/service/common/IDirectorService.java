package com.project.ensitech.service.common;


import com.project.ensitech.model.dto.DirectorDto;

import java.util.List;

public interface IDirectorService {
    DirectorDto createDirector(DirectorDto dto);
    List<DirectorDto> getAllDirectors();
    DirectorDto getDirectorById(Long id);
    DirectorDto updateDirector(Long id, DirectorDto dto);
    void deleteDirector(Long id);
}
