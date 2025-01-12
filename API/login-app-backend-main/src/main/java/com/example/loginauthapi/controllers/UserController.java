package com.example.loginauthapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.domain.Album;
import com.example.loginauthapi.dto.UpdateProfileDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.dto.AlbumDTO;
import com.example.loginauthapi.repositories.UserRepository;
import com.example.loginauthapi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Endpoint to get a user's profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.getUserWithAlbums(id); // Fetch user and initialize albums
        return ResponseEntity.ok(user);
    }

    // Endpoint to update a user's profile
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Long id,
            @RequestBody UpdateProfileDTO updateProfileDTO) {
        try {
            // Update the user
            User updatedUser = userService.updateUser(id, updateProfileDTO);

            // Return a response
            ResponseDTO response = new ResponseDTO(
                    updatedUser.getName(),
                    "sampleToken", // replace with actual token if needed
                    updatedUser.getId()
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Return an error response if user not found or any other error
            ResponseDTO response = new ResponseDTO("Error", e.getMessage(), null);
            return ResponseEntity.status(404).body(response);
        }
    }
}
