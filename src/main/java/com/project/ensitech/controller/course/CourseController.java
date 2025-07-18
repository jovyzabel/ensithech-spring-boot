package com.project.ensitech.controller.course;

import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.service.common.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/cours")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService iCourseService;

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto dto) {
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

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable Long id) {
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
