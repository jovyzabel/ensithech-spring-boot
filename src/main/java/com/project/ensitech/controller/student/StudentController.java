package com.project.ensitech.controller.student;

import com.project.ensitech.model.dto.StudentDto;
import com.project.ensitech.service.common.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students") // Endpoint de base pour les étudiants
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    // ... Vos endpoints CRUD existants (POST, GET, PUT, DELETE) ...

    /**
     * Associe une liste de cours à un étudiant spécifique.
     * @param studentId L'ID de l'étudiant.
     * @param courseIds La liste des IDs des cours à associer.
     * @return Le DTO de l'étudiant mis à jour.
     */
    @PutMapping("/{studentId}/courses")
    public ResponseEntity<StudentDto> associateCoursesToStudent(
            @PathVariable Long studentId,
            @RequestBody List<Long> courseIds) {

        StudentDto updatedStudent = studentService.associateCoursesToStudent(studentId, courseIds);
        return ResponseEntity.ok(updatedStudent);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);
        // On retourne une réponse vide avec un statut 204 (No Content),
        // ce qui est la norme pour une suppression réussie.
        return ResponseEntity.noContent().build();
    }
}


