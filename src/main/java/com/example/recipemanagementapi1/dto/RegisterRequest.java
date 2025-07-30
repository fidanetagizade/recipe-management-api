package com.example.recipemanagementapi1.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class RegisterRequest {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    @Email
    @NotBlank
    private String email;


    @Positive
    private Integer age;
}
