package com.example.recipemanagementapi1.dto;

import com.example.recipemanagementapi1.entity.Difficulty;
import lombok.Builder;
import lombok.Data;


@Builder
@Data

public class RecipeResponse {

    private String author;


    private String title;


    private String description;


    private Integer time;


    private Difficulty difficulty;
}
