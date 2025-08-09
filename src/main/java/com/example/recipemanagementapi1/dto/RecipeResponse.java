package com.example.recipemanagementapi1.dto;

import com.example.recipemanagementapi1.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor


public class RecipeResponse {

    private Long id;

    private String author;


    private String title;


    private String description;


    private Integer time;


    private Difficulty difficulty;

    private List<IngredientResponse> ingredients;
}