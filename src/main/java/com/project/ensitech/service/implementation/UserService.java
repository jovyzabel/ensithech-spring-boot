package com.project.ensitech.service.implementation;


import com.project.ensitech.exception.UserAlreadyExistsException;
import com.project.ensitech.exception.UserNotFoundException;
import com.project.ensitech.model.dto.UserDto;
import com.project.ensitech.model.entity.User;
import com.project.ensitech.repository.UserRepository;
import com.project.ensitech.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    /**
     * Crée un nouvel utilisateur
     */

/*    public UserDto createUser(UserDto.CreateUserRequest request) {
        logger.info("Création d'un nouvel utilisateur avec email: {}", request.getEmail());

        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Un utilisateur avec cet email existe déjà: " + request.getEmail());
        }

        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail());
        User savedUser = userRepository.save(user);

        logger.info("Utilisateur créé avec succès, ID: {}", savedUser.getId());
        return userMapper.toDto(savedUser);
    }

    /**
     * Récupère un utilisateur par son ID
     */

  /*  @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        logger.debug("Récupération de l'utilisateur avec ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec ID: " + id));

        return userMapper.toDto(user);
    }

    /**
     * Récupère un utilisateur par son email
     */

    /* @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {
        logger.debug("Récupération de l'utilisateur avec email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec email: " + email));

        return userMapper.toDto(user);
    }

    /**
     * Récupère tous les utilisateurs actifs
     */
    /*@Transactional(readOnly = true)
    public List<UserDto> getAllActiveUsers() {
        logger.debug("Récupération de tous les utilisateurs actifs");

        return userRepository.findByActiveTrue()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère tous les utilisateurs avec pagination
     */
    /*@Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        logger.debug("Récupération de tous les utilisateurs avec pagination");

        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    /**
     * Met à jour un utilisateur
     */
    /*public UserDto updateUser(Long id, UserDto.UpdateUserRequest request) {
        logger.info("Mise à jour de l'utilisateur avec ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec ID: " + id));

        // Vérifier si le nouvel email n'est pas déjà utilisé par un autre utilisateur
        if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new UserAlreadyExistsException("Un utilisateur avec cet email existe déjà: " + request.getEmail());
            }
        }

        // Mettre à jour les champs non nuls
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        User updatedUser = userRepository.save(user);

        logger.info("Utilisateur mis à jour avec succès, ID: {}", updatedUser.getId());
        return userMapper.toDto(updatedUser);
    }

    /**
     * Désactive un utilisateur
     */
    /* public UserDto deactivateUser(Long id) {
        logger.info("Désactivation de l'utilisateur avec ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec ID: " + id));

        user.deactivate();
        User deactivatedUser = userRepository.save(user);

        logger.info("Utilisateur désactivé avec succès, ID: {}", deactivatedUser.getId());
        return userMapper.toDto(deactivatedUser);
    }

    /**
     * Active un utilisateur
     */
    /*public UserDto activateUser(Long id) {
        logger.info("Activation de l'utilisateur avec ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec ID: " + id));

        user.activate();
        User activatedUser = userRepository.save(user);

        logger.info("Utilisateur activé avec succès, ID: {}", activatedUser.getId());
        return userMapper.toDto(activatedUser);
    }

    /**
     * Supprime un utilisateur
     */
    /* public void deleteUser(Long id) {
        logger.info("Suppression de l'utilisateur avec ID: {}", id);

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Utilisateur non trouvé avec ID: " + id);
        }

        userRepository.deleteById(id);
        logger.info("Utilisateur supprimé avec succès, ID: {}", id);
    }

    /**
     * Recherche des utilisateurs par nom
     */
    /* @Transactional(readOnly = true)
    public List<UserDto> searchUsersByName(String searchTerm) {
        logger.debug("Recherche d'utilisateurs par nom: {}", searchTerm);

        return userRepository.findByFirstNameOrLastNameContainingIgnoreCase(searchTerm)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Compte le nombre d'utilisateurs actifs
     */
    /*@Transactional(readOnly = true)
    public long countActiveUsers() {
        return userRepository.countActiveUsers();
    }

     */
}