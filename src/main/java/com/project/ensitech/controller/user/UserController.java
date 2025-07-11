package com.project.ensitech.controller.user;

import com.project.ensitech.model.dto.UserDto;
import com.project.ensitech.model.entity.User;
import com.project.ensitech.repository.UserRepository;
import com.project.ensitech.service.implementation.UserService;
import com.project.ensitech.service.implementation.UserServiceImpl;
import com.project.ensitech.service.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userServiceImpl, UserMapper userMapper) {
        this.userServiceImpl = userServiceImpl;
        this.userMapper=userMapper;
    }

    @GetMapping("users")
    public List<User> userList(){
        return userServiceImpl.getAll();
    }

//    @PostMapping("users")
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto.CreateUserRequest request) {
//        UserDto newUser = userServiceImpl.createUser(request);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED); // Return 201 Created status
//    }
}
