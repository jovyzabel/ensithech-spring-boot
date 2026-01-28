package com.project.ensitech.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "courses") // Exclure la collection pour éviter les boucles infinies
@DiscriminatorValue("2")
public class Student extends Person {

    private String matricule;

    // --- NOUVELLE RELATION MANY-TO-MANY ---
    @ManyToMany(fetch = FetchType.LAZY) // LAZY est important pour la performance
    @JoinTable(
            name = "student_courses", // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "student_id"), // Clé étrangère vers Student
            inverseJoinColumns = @JoinColumn(name = "course_id") // Clé étrangère vers Course
    )
    @JsonIgnoreProperties("students") // Quand tu sérialises un cours, ignore sa liste d'étudiants
//    private List<Course> courses;  // Un enseignant a plusieurs cours
    private Set<Course> courses = new HashSet<>();
}