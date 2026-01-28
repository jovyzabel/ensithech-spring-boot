package com.project.ensitech.service.common;


import com.project.ensitech.model.dto.StudyManagerDto;

import java.util.List;

public interface IStudyManagerService {
    StudyManagerDto createStudyManager(StudyManagerDto dto);
    List<StudyManagerDto> getAllStudyManagers();
    StudyManagerDto getStudyManagerById(Long id);
    StudyManagerDto updateStudyManager(Long id, StudyManagerDto dto);
    void deleteStudyManager(Long id);
}
