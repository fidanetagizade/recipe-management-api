package com.example.recipemanagementapi1.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstname;

    @NotBlank
    @Column(nullable = false)
    private String lastname;

    @NotBlank
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;


    @Positive
    private Integer age;



}
