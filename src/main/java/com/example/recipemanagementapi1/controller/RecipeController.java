package com.example.recipemanagementapi1.controller;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;
import com.example.recipemanagementapi1.entity.Difficulty;
import com.example.recipemanagementapi1.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<Set<RecipeResponse>> getAllRecipes() {
        Set<RecipeResponse> recipes = recipeService.getRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody @Valid RecipeRequest request) {
        RecipeResponse response = recipeService.createRecipe(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeResponse> updateRecipe(
            @PathVariable Long id,
            @RequestBody @Valid RecipeRequest request) {

        RecipeResponse updatedRecipe = recipeService.updateRecipe(id, request);
        return ResponseEntity.ok(updatedRecipe);
    }

    @GetMapping("/difficulty/{level}")
    public ResponseEntity<Set<RecipeResponse>> getRecipesByDifficulty(@PathVariable Difficulty level) {
        Set<RecipeResponse> recipes = recipeService.getRecipesByDifficulty(level);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecipeResponse>> getRecipesByUserId() {
        List<RecipeResponse> recipes = recipeService.getRecipesByUserId();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/recipes/ingredient/{name}")
    public ResponseEntity<List<RecipeResponse>> getRecipesByIngredient(@PathVariable String name) {
        List<RecipeResponse> responses = recipeService.getRecipesByIngredient(name);
        return ResponseEntity.ok(responses);
    }

}