package com.project.ensitech.service.common;

import com.project.ensitech.model.entity.User;

import java.util.List;

public interface IUser {
    User createUser(String nom, String email);
    List<User> getAll();
}
