package com.example.recipemanagementapi1.service;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RecipeService {
    RecipeResponse createRecipe(@Valid RecipeRequest recipeResponse);
    Set<RecipeResponse> getRecipesById();

}
