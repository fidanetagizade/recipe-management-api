package com.example.recipemanagementapi1.dto;


import lombok.Data;

@Data
public class IngredientRequest {

    private String name;

    private Double quantity;

    private String unit;

}
