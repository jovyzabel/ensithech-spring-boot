package com.project.ensitech.model.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Important pour l'héritage !
@DiscriminatorValue("4")

public class Director extends StudyManager{
}
