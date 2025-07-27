package com.project.ensitech.service.implementation;

import com.project.ensitech.exception.ResourceNotFoundException;
import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Teacher;
import com.project.ensitech.repository.PersonRepository;
import com.project.ensitech.service.mapper.TeacherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    // On mock les dépendances. On ne veut pas tester le repository ou le mapper,
    // mais seulement le service.
    @Mock
    private PersonRepository personRepository;

    @Mock
    private TeacherMapper teacherMapper;

    // On injecte les mocks dans notre service. C'est l'objet que nous allons tester.
    @InjectMocks
    private TeacherServiceImpl teacherService;

    private Teacher teacher;
    private TeacherDto teacherDto;
    private Long teacherId = 1L;

    @BeforeEach
    void setUp() {
        // On initialise des objets de test COHÉRENTS.
        // L'entité et le DTO représentent le même enseignant.
        teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFirstName("Sido");
        teacher.setLastName("Djuissi");
        teacher.setEmail("sifohouo@gmail.com");

        teacherDto = new TeacherDto();
        teacherDto.setFirstName("Sido");
        teacherDto.setLastName("Djuissi");
        teacherDto.setEmail("sifohouo@gmail.com");
    }

    @Test
    void createTeacher_shouldReturnTeacherDto() {
        // Arrange
        when(teacherMapper.toEntity(any(TeacherDto.class))).thenReturn(teacher);
        when(personRepository.save(any(Teacher.class))).thenReturn(teacher);
        when(teacherMapper.toDto(any(Teacher.class))).thenReturn(teacherDto);

        // Act
        TeacherDto resultDto = teacherService.createTeacher(teacherDto);

        // Assert
        assertNotNull(resultDto);
        // CORRECTION : L'assertion doit correspondre aux données de test.
        assertThat(resultDto.getFirstName()).isEqualTo("Sido");
        verify(personRepository, times(1)).save(teacher);
    }

    @Test
    void getAllTeachers_shouldReturnListOfTeacherDtos() {
        // Arrange
        List<Teacher> teachers = Collections.singletonList(teacher);
        List<TeacherDto> teacherDtos = Collections.singletonList(teacherDto);

        when(personRepository.findAllTeachers()).thenReturn(teachers);
        when(teacherMapper.toDtoList(teachers)).thenReturn(teacherDtos);

        // Act
        List<TeacherDto> resultDtos = teacherService.getAllTeachers();

        // Assert
        assertThat(resultDtos).isNotNull();
        assertThat(resultDtos.size()).isEqualTo(1);
        // CORRECTION : L'assertion doit correspondre aux données de test.
        assertThat(resultDtos.get(0).getFirstName()).isEqualTo("Sido");
    }

    @Test
    void getTeacherById_whenTeacherExists_shouldReturnTeacherDto() {
        // Arrange
        when(personRepository.findTeacherById(teacherId)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);

        // Act
        TeacherDto resultDto = teacherService.getTeacherById(teacherId);

        // Assert
        assertNotNull(resultDto);
        // CORRECTION : L'assertion doit correspondre aux données de test.
        assertThat(resultDto.getEmail()).isEqualTo("sifohouo@gmail.com");
    }

    // NOUVEAU TEST : Valide le cas où l'enseignant n'existe pas.
    @Test
    void getTeacherById_whenTeacherDoesNotExist_shouldThrowResourceNotFoundException() {
        // Arrange
        long nonExistentId = 99L;
        when(personRepository.findTeacherById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        // On vérifie que l'appel de la méthode lève bien l'exception attendue.
        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.getTeacherById(nonExistentId);
        });
    }


    @Test
    void updateTeacher_shouldReturnUpdatedTeacherDto() {
        // Arrange
        TeacherDto updateInfo = new TeacherDto(); // DTO avec les nouvelles infos
        updateInfo.setFirstName("Sido Mise à jour");

        Teacher updatedTeacherEntity = new Teacher(); // L'entité après sauvegarde
        updatedTeacherEntity.setId(teacherId);
        updatedTeacherEntity.setFirstName("Sido Mise à jour");

        TeacherDto finalUpdatedDto = new TeacherDto(); // Le DTO final retourné
        finalUpdatedDto.setFirstName("Sido Mise à jour");

        when(personRepository.findTeacherById(teacherId)).thenReturn(Optional.of(teacher));
        when(personRepository.save(any(Teacher.class))).thenReturn(updatedTeacherEntity);
        when(teacherMapper.toDto(updatedTeacherEntity)).thenReturn(finalUpdatedDto);

        // Act
        TeacherDto resultDto = teacherService.updateTeacher(teacherId, updateInfo);

        // Assert
        assertThat(resultDto).isNotNull();
        assertThat(resultDto.getFirstName()).isEqualTo("Sido Mise à jour");
        verify(personRepository, times(1)).save(any(Teacher.class));
    }

    // NOUVEAU TEST : Valide la mise à jour sur un ID inexistant.
    @Test
    void updateTeacher_whenTeacherDoesNotExist_shouldThrowResourceNotFoundException() {
        long nonExistentId = 99L;
        when(personRepository.findTeacherById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.updateTeacher(nonExistentId, teacherDto);
        });

        verify(personRepository, never()).save(any());
    }

    @Test

    void deleteTeacher_shouldCallDeleteById() { // Le nom du test pourrait être changé en "deleteTeacher_shouldDeleteTeacher"
        // Arrange
        when(personRepository.findTeacherById(teacherId)).thenReturn(Optional.of(teacher));
        // CORRECTION : On ne mock plus deleteById, mais delete(entity)
        doNothing().when(personRepository).delete(any(Teacher.class));

        // Act
        teacherService.deleteTeacher(teacherId);

        // Assert
        // CORRECTION : On vérifie que la méthode delete(entity) a été appelée, et non deleteById(id).
        verify(personRepository, times(1)).findTeacherById(teacherId); // On peut aussi vérifier que la recherche a eu lieu
        verify(personRepository, times(1)).delete(teacher); // On vérifie que la suppression a été appelée avec la bonne entité
    }
    // NOUVEAU TEST : Valide la suppression sur un ID inexistant.
    @Test
    void deleteTeacher_whenTeacherDoesNotExist_shouldThrowResourceNotFoundException() {
        long nonExistentId = 99L;
        when(personRepository.findTeacherById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            teacherService.deleteTeacher(nonExistentId);
        });

        verify(personRepository, never()).deleteById(anyLong());
    }
}