package com.example.recipemanagementapi1.controller;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;
import com.example.recipemanagementapi1.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

   @GetMapping
   public ResponseEntity<RecipeResponse> getRecipeById(@RequestParam Long id) {
       return null;
   }



    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody @Valid RecipeRequest request) {
        RecipeResponse response = recipeService.createRecipe(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}




