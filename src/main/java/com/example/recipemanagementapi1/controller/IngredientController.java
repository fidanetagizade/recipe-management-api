package com.example.recipemanagementapi1.controller;


import com.example.recipemanagementapi1.dto.IngredientRequest;
import com.example.recipemanagementapi1.dto.IngredientResponse;
import com.example.recipemanagementapi1.service.IngredientService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@Data
public class IngredientController {

    private final IngredientService ingredientService;



    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@RequestBody IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.createIngredient(request));
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<IngredientResponse>> getIngredientsByRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(ingredientService.getIngredientsByRecipe(recipeId));
    }

}
