package com.project.ensitech.service.implementation;

import com.project.ensitech.exception.ResourceNotFoundException;
import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.model.entity.Course;
import com.project.ensitech.repository.CourseRepository;
import com.project.ensitech.service.common.ICourseService;
import com.project.ensitech.service.mapper.CourseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository repository;
    private final CourseMapper mapper;

    @Override
    public CourseDto createCourse(CourseDto dto) {
        Course course = mapper.toEntity(dto);
        return mapper.toDto(repository.save(course));
    }
    @Override
    public CourseDto updateCourse( CourseDto courseDto) {
        Course existing = repository.findById(courseDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseDto.getId()));

        // Update fields
        existing.setIntitule(courseDto.getIntitule());
        existing.setCoefficient(courseDto.getCoefficient());
        existing.setNombreHeures(courseDto.getNombreHeures());

        Course updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public CourseDto getCourse(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec id: " + id));
        return mapper.toDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Cours non trouvé avec id: " + id);
        }
        repository.deleteById(id);
    }
}