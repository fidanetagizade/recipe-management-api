package com.example.recipemanagementapi1.service;

import com.example.recipemanagementapi1.dto.LoginRequest;
import com.example.recipemanagementapi1.dto.RegisterRequest;

public interface UserService {

    String registerUser(RegisterRequest registerRequest);
    String loginUser(LoginRequest loginRequest);
}


