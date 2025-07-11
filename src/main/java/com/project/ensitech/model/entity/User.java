package com.project.ensitech.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String email;

    public User() {}

    public User(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

}
