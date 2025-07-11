package com.project.ensitech.service.mapper;

import com.project.ensitech.model.dto.UserDto;
import com.project.ensitech.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDto.
     * This is used when returning user data from the API.
     */
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getName(), // Ensure User entity has a 'name' field and getName() method
                user.getEmail() // Ensure User entity has an 'email' field and getEmail() method
        );
    }

    /**
     * Converts a UserDto to a User entity.
     * This might be used for updating existing users if the DTO includes an ID.
     */
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }

    /**
     * Converts a CreateUserRequest DTO to a User entity.
     * This is used when creating a new user from an incoming request.
     */
    public User toEntity(UserDto.CreateUserRequest request) {
        if (request == null) {
            return null;
        }
        // This relies on your User entity having a constructor like:
        // public User(String name, String email)
        // Ensure request.getFirstName() maps to the 'name' field of your User entity.
        return new User(
                request.getFirstName(), // Maps to 'name' field in User entity
                request.getEmail()
        );
    }
}