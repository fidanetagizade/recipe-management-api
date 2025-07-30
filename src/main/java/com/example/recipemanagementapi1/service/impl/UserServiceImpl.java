package com.example.recipemanagementapi1.service.impl;


import com.example.recipemanagementapi1.dto.LoginRequest;
import com.example.recipemanagementapi1.dto.RegisterRequest;
import com.example.recipemanagementapi1.entity.User;
import com.example.recipemanagementapi1.exception.EmailAlreadyExistsException;
import com.example.recipemanagementapi1.exception.InvalidCredentialsException;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.security.JwtService;
import com.example.recipemanagementapi1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public String registerUser(RegisterRequest registerRequest) {
        String password = registerRequest.getPassword();
        if (password == null || password.length() < 8) {
            throw new RuntimeException("Password must be at least 8 characters");
        }
        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");

        }
        User user = User.builder()
                .id(null)
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .age(registerRequest.getAge())
                .build();


        userRepository.save(user);
        return jwtService.generateToken(registerRequest.getEmail());
    }

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Optional<User> existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if (!existingUser.isPresent()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        User user = existingUser.get();
        if (loginRequest.getPassword() == user.getPassword()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return jwtService.generateToken(loginRequest.getEmail());


    }

}


