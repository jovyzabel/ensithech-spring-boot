package com.project.ensitech.controller.course;

import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.service.common.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController //This class handles HTTP requests.
@RequestMapping("/api/cours") //All endpoints start with this base path.
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService iCourseService;

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto dto) { //@RequestBody: Accept JSON input in POST requests. @Valid:  trigger validation on the CourseDto object that is passed in the request body.
       // System.out.println("ddd Controller"+ dto.toString());
        CourseDto created = iCourseService.createCourse( dto);
        // return ResponseEntity.ok(iCourseService.createCourse(dto));
        return ResponseEntity.ok(created);
    }
    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody CourseDto courseDto) {
        CourseDto updated = iCourseService.updateCourse( courseDto);
        return ResponseEntity.ok(updated);
    }
    /**
     * Associer un enseignant existant à un cours existant
     */
    @PutMapping("/{courseId}/assign-teacher/{teacherId}")
    public ResponseEntity<CourseDto> assignTeacher(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) {
        CourseDto updated = iCourseService.assignTeacherToCourse(courseId, teacherId);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable Long id) {//@PathVariable: Extract ID from the URL.
        return ResponseEntity.ok(iCourseService.getCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(iCourseService.getAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iCourseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
