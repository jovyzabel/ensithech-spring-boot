package com.project.ensitech.service.implementation;

import com.project.ensitech.model.dto.SpecialityDto;
import com.project.ensitech.model.entity.Speciality;
import com.project.ensitech.repository.SpecialityRepository;
import com.project.ensitech.service.common.ISpecialityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityImpl implements ISpecialityService {
    private final SpecialityRepository specialityRepository;

    private final static Logger logger = LoggerFactory.getLogger(SpecialityImpl.class);

    public SpecialityImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public Speciality create(SpecialityDto dto) {
        Speciality speciality = new Speciality();
        speciality.setLabel(dto.getLabel());
        speciality.setDescription(dto.getDescription());
        speciality.setCycle(dto.getCycle());

        return this.specialityRepository.save(speciality);

    }

    @Override
    public Speciality update(int id, SpecialityDto dto){
        Speciality speciality = get(id);

            if(speciality != null){
                speciality.setLabel(dto.getLabel());
                speciality.setDescription(dto.getDescription());
                speciality.setCycle(dto.getCycle());

                return this.specialityRepository.save(speciality);
            }
        else{
                logger.error("Cette specialité n'existe pas !");
                return null;
            }
    }

    @Override
    public Speciality get(int id) {
        return this.specialityRepository.findSpecialityById(id);
    }

    @Override
    public List<Speciality> getAll() {
        return this.specialityRepository.findAllSpecialities();
    }

    @Override
    public void delete(int id) {
        this.specialityRepository.deleteById(id);
    }
}
