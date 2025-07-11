package com.project.ensitech.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String nom;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

//    @NotBlank(message = "Le nom ne peut pas être vide")
//    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
//    private String lastName;
//
//
//    private Boolean active;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime updatedAt;
//
    // Constructeurs
    public UserDto(Long id, String nom, String email) {}
//
    public UserDto(String firstName, String email) {
        this.nom = firstName;
        this.email = email;
    }
//
//    public UserDto(Long id, String firstName, String lastName, String email, Boolean active,
//                   LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.active = active;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    // Getters et Setters
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getFirstName() { return firstName; }
//    public void setFirstName(String firstName) { this.firstName = firstName; }
//
//    public String getLastName() { return lastName; }
//    public void setLastName(String lastName) { this.lastName = lastName; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public Boolean getActive() { return active; }
//    public void setActive(Boolean active) { this.active = active; }
//
//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
//
//    public LocalDateTime getUpdatedAt() { return updatedAt; }
//    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
//
//    // Méthodes utilitaires
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//
//    // Classe interne pour la création d'utilisateur

    public static class CreateUserRequest {
        @NotBlank(message = "Le prénom ne peut pas être vide")
        @Size(min = 2, max = 50)
        private String firstName;

//        @NotBlank(message = "Le nom ne peut pas être vide")
//        @Size(min = 2, max = 50)
//        private String lastName;

        @Email(message = "L'email doit être valide")
        @NotBlank(message = "L'email ne peut pas être vide")
        private String email;

        public CreateUserRequest() {}

        public CreateUserRequest(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.email = email;
        }
//
        // Getters et Setters
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

//        public String getLastName() { return lastName; }
//        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
//
//    // Classe interne pour la mise à jour d'utilisateur
//    public static class UpdateUserRequest {
//        @Size(min = 2, max = 50)
//        private String firstName;
//
//        @Size(min = 2, max = 50)
//        private String lastName;
//
//        @Email(message = "L'email doit être valide")
//        private String email;
//
//        public UpdateUserRequest() {}
//
//        // Getters et Setters
//        public String getFirstName() { return firstName; }
//        public void setFirstName(String firstName) { this.firstName = firstName; }
//
//        public String getLastName() { return lastName; }
//        public void setLastName(String lastName) { this.lastName = lastName; }
//
//        public String getEmail() { return email; }
//        public void setEmail(String email) { this.email = email; }
//    }
}