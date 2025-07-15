package com.project.ensitech.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set; // On utilise un Set pour les relations pour éviter les doublons

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important pour l'héritage !
// @DiscriminatorValue("1") dit à Hibernate : "Si la colonne 'person_type' contient la valeur 1, alors cette ligne est un Teacher".
@DiscriminatorValue("1")
public class Teacher extends Person {
    @Temporal(TemporalType.TIMESTAMP) // Ici on veut la date et l'heure de création.
    private Date createdAt;
    // --- Préparation pour le futur : Associer des cours ---
    // Un enseignant (One) peut enseigner plusieurs cours (Many).
    // 'mappedBy = "teacher"' indique que l'entité Course gère la relation (elle aura un champ 'teacher').
    // @OneToMany(mappedBy = "teacher")
    // private Set<Course> courses;
}
