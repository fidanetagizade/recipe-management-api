package com.example.recipemanagementapi1.dto;


import com.example.recipemanagementapi1.entity.Difficulty;
import lombok.Data;

import java.util.List;


@Data

public class RecipeRequest {

    private String title;

    private String description;

    private Integer time;

    private Difficulty difficulty;

    private List<IngredientResponse> ingredients;
}
