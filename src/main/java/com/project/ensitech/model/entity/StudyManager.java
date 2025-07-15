package com.project.ensitech.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important pour l'héritage !
@DiscriminatorValue("3") // Valeur pour le StudyManager
public class StudyManager extends Person {

}
