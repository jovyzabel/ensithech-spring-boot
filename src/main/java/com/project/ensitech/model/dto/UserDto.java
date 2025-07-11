package com.project.ensitech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserDto: Used for returning user data, includes ID

public class UserDto {
    private Long id;
    private String name; // Matches User entity's 'name'
    private String email;

    public UserDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class CreateUserRequest {
        private String firstName; // This field name is used in your UserMapper's toEntity method
        private String email;

        public CreateUserRequest() {
        }

        public CreateUserRequest(String firstName, String email) {
            this.firstName = firstName;
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}