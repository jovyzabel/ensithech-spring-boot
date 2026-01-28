package com.project.ensitech.service.common;

import com.project.ensitech.model.dto.SpecialityDto;
import com.project.ensitech.model.entity.Speciality;
import com.project.ensitech.repository.SpecialityRepository;

import java.util.List;

public interface ISpecialityService {
    public Speciality create(SpecialityDto dto);

    public Speciality update(int id, SpecialityDto dto);

    public Speciality get(int id);

    public List<Speciality> getAll();

    public void delete(int id);
}
