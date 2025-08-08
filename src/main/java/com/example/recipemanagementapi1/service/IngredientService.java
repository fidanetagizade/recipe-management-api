package com.example.recipemanagementapi1.service;

import com.example.recipemanagementapi1.dto.IngredientRequest;
import com.example.recipemanagementapi1.dto.IngredientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IngredientService {


    IngredientResponse createIngredient(IngredientRequest request);

    List<IngredientResponse> getIngredientsByRecipe(Long recipeId);

    IngredientResponse getIngredientById(Long id);

    IngredientResponse updateIngredient(Long id, IngredientRequest request);

    void deleteIngredient(Long id);
}
