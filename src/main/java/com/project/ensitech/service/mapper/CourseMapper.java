package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.model.entity.Course;
import org.springframework.stereotype.Component;

// MapStruct
@Component
public class CourseMapper {

    public CourseDto toDto(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cours ne peut pas être null");
        }
        return new CourseDto(
                course.getId(),
                course.getIntitule(),
                course.getCoefficient(),
                course.getNombreHeures()
        );
    }

    public Course toEntity(CourseDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO ne peut pas être null");
        }
        return Course.builder()
                .id(dto.getId())
                .intitule(dto.getIntitule())
                .coefficient(dto.getCoefficient())
                .nombreHeures(dto.getNombreHeures())
                .build();
    }
}

    /*@Mapper(componentModel = "spring")
    public interface CourseMapper {
        CourseDto toDto(Course course);
        Course toEntity(CourseDto dto);
    }*/
