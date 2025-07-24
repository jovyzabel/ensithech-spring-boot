package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// MapStruct
@RequiredArgsConstructor
@Component
public class CourseMapper {
    private final TeacherMapper teacherMapper;
    public CourseDto toDto(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Cours ne peut pas être null");
        }
        //TeacherDto teacherDto = null;
        Long teacherId = null;
        // System.out.println("mon teacher "+ course.getTeacher().toString());
        if (course.getTeacher() != null) {
             //teacherDto =  teacherMapper.toDto(course.getTeacher());
             // teacherId = teacherDto.getId();
             teacherId = course.getTeacher().getId();
        }

        return new CourseDto(
                course.getId(),
                course.getIntitule(),
                course.getCoefficient(),
                course.getNombreHeures(),
                teacherId
                //teacherDto
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
