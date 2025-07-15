package com.project.ensitech.model.dto;

import com.project.ensitech.enumeration.Gender;
import lombok.Data;

import java.util.Date;

// Renommer en TeacherDto serait plus clair, mais gardons UserPersonDto pour l'instant.
@Data
public class TeacherDto {
    private String firstName;
    private  String lastName;
    private String email;
    private String address;
    private String telephone;
    private Date birthday;
    private Gender gender;// Ce champ manquait, il faut l'ajouter
    // Le champ 'createdAt' n'est pas dans le DTO car il est géré par le serveur.
}
