package com.project.ensitech.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ensitech.enumeration.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class StudentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // L'ID n'est pas fourni à la création
    private Long id;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom de famille est obligatoire")
    private String lastName;

    @Email(message = "Le format de l'email est invalide")
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    private String address;
    private String telephone;

    @Past(message = "La date de naissance doit être dans le passé")
    private Date birthday;

    @NotNull(message = "Le genre est obligatoire")
    private Gender gender;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Le matricule est généré automatiquement
    private String matricule;
}