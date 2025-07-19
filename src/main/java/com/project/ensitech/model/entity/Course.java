package com.project.ensitech.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
// Modèle de base de données
@Data
@Entity
@Table(name = "cours")
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
