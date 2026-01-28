package com.project.ensitech.model.entity;

import com.project.ensitech.enumeration.Cycle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Speciality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Ce champ doit être remplit")
    private String label ;

    @NotBlank(message = "Ce champ doit être remplit")
    private String description;

    @NotNull(message = "Ce champ ne peut pas être null")
    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private Cycle cycle;
}


