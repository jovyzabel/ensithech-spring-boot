package com.project.ensitech.model.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
//@Getter
//@Setter
// Data Transfer Object
public class CourseDto {
    private Long id;
    private String title; // <-- RENOMMER 'intitule' en 'title'pour la correspondance avec le front
    private Integer coefficient;
    private Integer hours; // <-- RENOMMER 'nombreHeures' en 'hours' (plus court)
    private Long teacherId;
    // private TeacherDto teacher;
}
