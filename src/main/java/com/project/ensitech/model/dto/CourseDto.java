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
    private String intitule;
    private Integer coefficient;
    private Integer nombreHeures;
}
