package com.example.recipemanagementapi1.service;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;
import com.example.recipemanagementapi1.entity.Difficulty;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;

@Service
public interface RecipeService {
    RecipeResponse createRecipe(@Valid RecipeRequest recipeResponse);
    Set<RecipeResponse> getRecipes();
    public void deleteRecipe(Long id);
    RecipeResponse updateRecipe(Long id, @Valid RecipeRequest request);
    Set<RecipeResponse> getRecipesByDifficulty(Difficulty level);
    List<RecipeResponse> getRecipesByUserId();
}
