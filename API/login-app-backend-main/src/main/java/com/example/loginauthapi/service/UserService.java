package com.example.loginauthapi.service;

import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.dto.UpdateProfileDTO;
import com.example.loginauthapi.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Change the type to PasswordEncoder

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getUserWithAlbums(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Hibernate.initialize(user.getAlbums()); // Explicitly initialize the albums collection
        return user;
    }

    public User updateUser(Long id, UpdateProfileDTO updateProfileDTO) {
        // Fetch the user by ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        // Update the fields that are allowed
        if (updateProfileDTO.getUserName() != null)
            user.setName(updateProfileDTO.getUserName());
        if (updateProfileDTO.getUserUsername() != null)
            user.setUserName(updateProfileDTO.getUserUsername());
        if (updateProfileDTO.getUserGender() != null)
            user.setGender(updateProfileDTO.getUserGender());
        if (updateProfileDTO.getUserBday() != null)
            user.setBirthdate(Date.valueOf(updateProfileDTO.getUserBday()));
        if (updateProfileDTO.getUserCountry() != null)
            user.setCountry(updateProfileDTO.getUserCountry());
        if (updateProfileDTO.getAboutMe() != null)
            user.setAboutMe(updateProfileDTO.getAboutMe());
        if (updateProfileDTO.getUserImage() != null)
            user.setUserImage(updateProfileDTO.getUserImage().getBytes());

        // Save the updated user to the database
        return userRepository.save(user);

    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userName));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username); // Calls the repository to find the user
    }

}
