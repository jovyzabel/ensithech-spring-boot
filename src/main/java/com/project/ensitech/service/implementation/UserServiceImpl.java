package com.project.ensitech.service.implementation;




import com.project.ensitech.model.dto.UserDto;
import com.project.ensitech.model.entity.User;
import com.project.ensitech.repository.UserRepository;
import com.project.ensitech.service.common.IUser;
import com.project.ensitech.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUser {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper= userMapper;

    }

    @Override
    public User createUser(String nom, String email) {
        User utilisateur = new User(nom, email);
        return userRepository.save(utilisateur);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public UserDto createUser(UserDto.CreateUserRequest request) {
        // 1. Convert the incoming DTO (CreateUserRequest) to a User entity
        User userToSave = userMapper.toEntity(request);

        // 2. Save the User entity to the database using the repository
        User savedUser = userRepository.save(userToSave);

        // 3. Convert the saved User entity back to a UserDto for the response
        return userMapper.toDto(savedUser);
    }

}

