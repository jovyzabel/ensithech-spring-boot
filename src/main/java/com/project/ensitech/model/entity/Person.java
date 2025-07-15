package com.project.ensitech.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ensitech.enumeration.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.project.ensitech.enumeration.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
// L'annotation @Inheritance définit la stratégie. SINGLE_TABLE est efficace.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn indique le nom de la colonne qui va différencier les types de Personnes.
// Par défaut, le type de la colonne est STRING, mais vous avez mis INTEGER, c'est un choix valide.
@DiscriminatorColumn(name="person_type", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Person implements Serializable {
  //  @Serial private static final long serialVersionUID = -8885817712041252438L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY est souvent mieux pour MySQL, il laisse la BDD gérer l'auto-incrément.
  private Long id; // Utiliser Long pour les IDs est une bonne pratique, pour éviter les dépassements.

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(unique = true, nullable = false) // Un email doit être unique et non nul
  private String email;

  private String address;
  private String telephone;

  @Temporal(TemporalType.DATE) // Précise que seule la date (sans l'heure) nous intéresse.
  private Date birthday;

  @Enumerated(EnumType.STRING) // Stocke l'énumération comme une chaîne ("MALE", "FEMALE") dans la BDD, c'est plus lisible.
  @Column(nullable = false)
  private Gender gender;
}
