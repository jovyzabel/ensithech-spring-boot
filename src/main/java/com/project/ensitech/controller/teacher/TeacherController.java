package com.project.ensitech.controller.teacher;// package com.project.ensitech.controller.teacher;

import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Teacher;
import com.project.ensitech.service.common.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indique que cette classe est un controller REST qui retourne du JSON.
@RequestMapping("/api/teachers") // Toutes les URL de ce controller commenceront par /api/teachers.
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // On autorise explicitement l'origine de votre front-end Angular
public class TeacherController {

    private final ITeacherService teacherService;

    // POST /api/teachers -> Créer un enseignant
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDto dto) {
        Teacher newTeacher = teacherService.createTeacher(dto);
        // On retourne l'objet créé et un statut HTTP 201 (Created).
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // GET /api/teachers -> Lister tous les enseignants
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers); // Retourne la liste et un statut 200 (OK).
    }
    // GET /api/teachers/1 -> Lire un enseignant par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    // PUT /api/teachers/1 -> Modifier un enseignant par son ID
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto dto) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, dto);
        return ResponseEntity.ok(updatedTeacher);
    }

    // DELETE /api/teachers/1 -> Supprimer un enseignant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        // On retourne une réponse vide avec un statut 204 (No Content),
        // ce qui est la norme pour une suppression réussie.
        return ResponseEntity.noContent().build();
    }
}
