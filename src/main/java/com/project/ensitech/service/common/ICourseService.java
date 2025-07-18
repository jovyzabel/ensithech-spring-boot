package com.project.ensitech.service.common;

import com.project.ensitech.model.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    CourseDto createCourse(CourseDto dto);
    CourseDto updateCourse(CourseDto dto);
    CourseDto getCourse(Long id);
    List<CourseDto> getAllCourses();
    void deleteCourse(Long id);
}
