package com.project.ensitech.model.dto;

import com.project.ensitech.enumeration.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data  // Génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // Génère un constructeur sans arguments
@AllArgsConstructor // Génère un constructeur avec tous les champs (utile pour les tests)
public class TeacherDto {

    private Long id; // L'identifiant unique de l'enseignant
    private String firstName;
    private  String lastName;
    @Email(message = "Le format de l'email est invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;
    private String address;
    private String telephone;
    private Date birthday;
    private Gender gender;
    private List<CourseDto> courses;  // Liste des cours associés

    // Contructeur utiliser pour afficher l'enseignat associé à un cours
    public TeacherDto(Long id, String firstName, String lastName) {
    }
    // Le champ 'createdAt' n'est pas dans le DTO car il est géré par le serveur.
}
