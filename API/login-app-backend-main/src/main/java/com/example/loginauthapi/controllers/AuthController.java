package com.example.loginauthapi.controllers;

import com.example.loginauthapi.domain.User;
import com.example.loginauthapi.dto.LoginRequestDTO;
import com.example.loginauthapi.dto.RegisterRequestDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        Optional<User> userOpt = this.repository.findByEmail(body.email());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseDTO("User not found", null, null)); // Return error if user is not found
        }

        User user = userOpt.get();
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            // Include userId in the response DTO
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token, user.getId())); // Return name, token, and userId
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Invalid credentials", null, null)); // Return error if credentials are incorrect
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> userOpt = this.repository.findByEmail(body.email());
        if (userOpt.isPresent()) {
            return ResponseEntity.badRequest().body(new ResponseDTO("Email already in use", null, null)); // Return error if email already exists
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        newUser.setGender(body.gender());
        newUser.setUserName(body.userName());
        newUser.setCountry(body.country());
        newUser.setBirthdate(body.birthdate());
        newUser.setAboutMe(body.aboutMe());
        newUser.setUserImage(body.Image());
        this.repository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        // Return user details along with token and userId after successful registration
        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token, newUser.getId()));
    }
}
