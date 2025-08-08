package com.example.recipemanagementapi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


    @Entity
    @Table(name = "ingredients")
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class Ingredient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @NotBlank
        @Column(nullable = false, unique = true)
        private String name;


        @Column(nullable = false, unique = true)
        private Double quantity;

        @NotBlank
        @Column(nullable = false, unique = true)
        private String unit;

        @ManyToMany(mappedBy = "ingredients")
        private List<Recipe> recipes;


        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

    }

