package com.example.recipemanagementapi1.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class IngredientResponse {

    private Long id;
    private String name;
    private Double quantity;
    private String unit;



}
