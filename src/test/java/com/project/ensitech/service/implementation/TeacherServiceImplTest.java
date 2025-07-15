package com.project.ensitech.service.implementation;

import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Teacher;
import com.project.ensitech.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


// @ExtendWith(MockitoExtension.class) active l'utilisation des annotations Mockito comme @Mock et @InjectMocks.
@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    // @Mock crée une fausse implémentation (un "mock") du PersonRepository.
    // Toutes ses méthodes retourneront null ou des valeurs vides par défaut.
    // Nous allons lui dire comment se comporter dans chaque test.
    @Mock
    private PersonRepository personRepository;

    // @InjectMocks crée une instance réelle de TeacherServiceImpl, mais injecte
    // automatiquement les mocks (ici, personRepository) déclarés dans cette classe de test.
    @InjectMocks
    private TeacherServiceImpl teacherService;

    private Teacher teacher;
    private TeacherDto dto;

    // @BeforeEach est une méthode qui s'exécute AVANT chaque méthode de test (@Test).
    // C'est parfait pour initialiser des objets communs à plusieurs tests.
    @BeforeEach
    void setUp() {
        // On prépare un objet DTO pour nos tests
        dto = new TeacherDto();
        dto.setFirstName("Marie");
        dto.setLastName("Curie");
        dto.setEmail("marie.curie@univ.fr");

        // On prépare une entité Teacher correspondante
        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Marie");
        teacher.setLastName("Curie");
        teacher.setEmail("marie.curie@univ.fr");
        teacher.setCreatedAt(new Date());
    }

    @Test
    void createTeacher() {
        // --- ARRANGE (Préparation) ---
        // On configure le comportement de notre mock.
        // "QUAND la méthode `save` du `personRepository` est appelée avec n'importe quel objet de type Teacher,
        // ALORS retourne notre objet `teacher` pré-configuré."
        when(personRepository.save(any(Teacher.class))).thenReturn(teacher);

        // --- ACT (Action) ---
        // On exécute la méthode du service que l'on souhaite tester.
        Teacher result = teacherService.createTeacher(dto);

        // --- ASSERT (Vérification) ---
        // On vérifie que le résultat est celui attendu.
        assertNotNull(result, "Le Teacher créé ne devrait pas être null.");
        assertEquals(teacher.getId(), result.getId(), "L'ID du Teacher retourné devrait correspondre.");
        assertEquals("Marie", result.getFirstName(), "Le prénom devrait correspondre.");

        // On vérifie que la méthode `save` du repository a bien été appelée exactement une fois.
        // C'est un test important pour s'assurer que l'interaction avec la dépendance a bien eu lieu.
        verify(personRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    void getAllTeachers() {
        // --- ARRANGE (Préparation) ---
        // On configure le comportement de notre mock.
        // "QUAND la méthode `save` du `personRepository` est appelée avec n'importe quel objet de type Teacher,
        // ALORS retourne notre objet `teacher` pré-configuré."
        when(personRepository.save(any(Teacher.class))).thenReturn(teacher);

        // --- ACT (Action) ---
        // On exécute la méthode du service que l'on souhaite tester.
        Teacher result = teacherService.createTeacher(dto);

        // --- ASSERT (Vérification) ---
        // On vérifie que le résultat est celui attendu.
        assertNotNull(result, "Le Teacher créé ne devrait pas être null.");
        assertEquals(teacher.getId(), result.getId(), "L'ID du Teacher retourné devrait correspondre.");
        assertEquals("Marie", result.getFirstName(), "Le prénom devrait correspondre.");

        // On vérifie que la méthode `save` du repository a bien été appelée exactement une fois.
        // C'est un test important pour s'assurer que l'interaction avec la dépendance a bien eu lieu.
        verify(personRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    void getTeacherById() {

    }

    @Test
    void updateTeacher() {
    }

    @Test
    void deleteTeacher() {
    }
}