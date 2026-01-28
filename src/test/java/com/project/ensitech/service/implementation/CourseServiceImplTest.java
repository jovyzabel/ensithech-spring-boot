package com.project.ensitech.service.implementation;

import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.model.entity.Course;
import com.project.ensitech.repository.CourseRepository;
import com.project.ensitech.service.mapper.CourseMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private CourseMapper mapper;

    @InjectMocks
    private CourseServiceImpl service;

    @Test
    void shouldUpdateCourseSuccessfully() {
        // Given
        CourseDto inputDto = new CourseDto(1L, "Conception UML", 2, 40);
        Course existingCourse = Course.builder()
                .id(1L)
                .intitule("Agilité N1")
                .coefficient(1)
                .nombreHeures(30)
                .build();

        Course updatedCourse = Course.builder()
                .id(1L)
                .intitule("Agilité")
                .coefficient(2)
                .nombreHeures(40)
                .build();

        CourseDto expectedDto = new CourseDto(1L, "Math", 2, 40);

        // When
        when(repository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(repository.save(existingCourse)).thenReturn(updatedCourse);
        when(mapper.toDto(updatedCourse)).thenReturn(expectedDto);

        // Then
        CourseDto result = service.updateCourse(inputDto);

        assertEquals(expectedDto.getId(), result.getId());
        assertEquals(expectedDto.getIntitule(), result.getIntitule());
        assertEquals(expectedDto.getCoefficient(), result.getCoefficient());
        assertEquals(expectedDto.getNombreHeures(), result.getNombreHeures());

        verify(repository).findById(1L);
        verify(repository).save(existingCourse);
        verify(mapper).toDto(updatedCourse);
    }

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {
        // Given
        CourseDto inputDto = new CourseDto(99L, "Math", 2, 40);
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> service.updateCourse(inputDto));
        verify(repository).findById(99L);
        verify(repository, never()).save(any());
        verify(mapper, never()).toDto(any());
    }
}
