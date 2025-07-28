package com.project.ensitech.service.common;

import com.project.ensitech.model.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IStudentService {
    StudentDto createStudent(StudentDto studentDto);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);

    StudentDto associateCoursesToStudent(Long studentId, List<Long> courseIds);
}
