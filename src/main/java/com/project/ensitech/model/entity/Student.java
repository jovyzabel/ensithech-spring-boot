package com.project.ensitech.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important pour l'héritage !
@DiscriminatorValue("2") // Valeur pour l'étudiant
public class Student extends Person{
    // Cette propriété doit être générée de manière automatique
    // On le fera dans la couche Service lors de la création d'un étudiant.
    private String matricule;
}
