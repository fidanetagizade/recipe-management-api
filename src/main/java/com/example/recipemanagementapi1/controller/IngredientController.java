package com.example.recipemanagementapi1.controller;

import com.example.recipemanagementapi1.dto.IngredientRequest;
import com.example.recipemanagementapi1.dto.IngredientResponse;
import com.example.recipemanagementapi1.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;


    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@RequestBody IngredientRequest request) {
        IngredientResponse response = ingredientService.createIngredient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<IngredientResponse>> getIngredientsByRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(ingredientService.getIngredientsByRecipe(recipeId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientRequest request) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
