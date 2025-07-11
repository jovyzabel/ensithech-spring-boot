package com.project.ensitech.service.implementation;




import com.project.ensitech.model.entity.User;
import com.project.ensitech.repository.UserRepository;
import com.project.ensitech.service.common.IUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUser {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository utilisateurRepository) {
        this.userRepository = utilisateurRepository;
    }

    @Override
    public User creerUtilisateur(String nom, String email) {
        User utilisateur = new User(nom, email);
        return userRepository.save(utilisateur);
    }

    @Override
    public List<User> listerUtilisateurs() {
        return userRepository.findAll();
    }
}

