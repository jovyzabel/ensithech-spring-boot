package com.project.ensitech.model.entity;

import com.project.ensitech.enumeration.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set; // On utilise un Set pour les relations pour éviter les doublons

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important pour l'héritage !
// @DiscriminatorValue("1") dit à Hibernate : "Si la colonne 'person_type' contient la valeur 1, alors cette ligne est un Teacher".
@DiscriminatorValue("1")
public class Teacher extends Person {
    @Temporal(TemporalType.TIMESTAMP) // Ici on veut la date et l'heure de création.
    private Date createdAt;
    /* --- Préparation pour le futur : Associer des cours ---
     Un enseignant (One) peut enseigner plusieurs cours (Many).
    'mappedBy = "teacher"' indique que l'entité Course gère la relation (elle aura un champ 'teacher').*/
     @OneToMany(
             mappedBy = "teacher", // TRÈS IMPORTANT !
             cascade = CascadeType.ALL,
             orphanRemoval = true,
             fetch = FetchType.LAZY
     )
    private Set<Course> courses = new HashSet<>();;

    // LE CONSTRUCTEUR EXACT QUE HIBERNATE RECHERCHE
    public Teacher(Long id, String firstName, String lastName, String email, String address, String telephone, Date birthday, Gender gender, Date createdAt) {
        super(id, firstName, lastName, email, address, telephone, birthday, gender);
        this.createdAt = createdAt;
    }

}
