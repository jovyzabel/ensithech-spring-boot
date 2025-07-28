package com.project.ensitech.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Modèle de base de données
@Data
@Entity
@Table(name = "cours")
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"students", "teachers"})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "L’intitulé du cours  est obligatoire")
    private String intitule;

    @NotNull(message = "Le coefficient du cours est obligatoire")
    private Integer coefficient;

    @NotNull(message = "Le nombre d'heure du cours est obligatoire")
    private Integer nombreHeures;

    /*@ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    private Teacher teacher;*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = true) // Clé étrangère dans la table Cours

    @JsonIgnoreProperties("courses") // Quand on sérialise un Teacher, on ignore ses cours pour éviter la boucle
    private Teacher teacher;  // Chaque cours appartient à un enseignant


    // --- RELATION AVEC STUDENT (CORRIGÉE) ---
    // Cette relation est le "côté inverse" de celle définie dans Student.
    // 'mappedBy = "courses"' dit à Hibernate : "Ne crée pas de colonne ici,
    // la gestion de cette relation (la table de jointure) est définie
    // dans le champ 'courses' de l'entité Student".
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("courses") // Quand tu sérialises un étudiant, ignore sa liste de cours
//    private List<Student> students;
    private Set<Student> students = new HashSet<>();
}
