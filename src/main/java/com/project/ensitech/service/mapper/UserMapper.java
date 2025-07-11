package com.project.ensitech.service.mapper;


import com.project.ensitech.model.dto.UserDto;
import com.project.ensitech.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Convertit une entité User en UserDto
     */
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getNom(),
                user.getEmail()

        );
    }

    /**
     * Convertit un UserDto en entité User
     */
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setNom(userDto.getNom());

        user.setEmail(userDto.getEmail());


        return user;
    }

    /**
     * Convertit une CreateUserRequest en entité User
     */
    public User toEntity(UserDto.CreateUserRequest request) {
        if (request == null) {
            return null;
        }

        return new User(
                request.getFirstName(),
                request.getEmail()
        );
    }
}