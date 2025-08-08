package com.example.recipemanagementapi1.service.impl;

import com.example.recipemanagementapi1.dto.IngredientRequest;
import com.example.recipemanagementapi1.dto.IngredientResponse;
import com.example.recipemanagementapi1.entity.Ingredient;
import com.example.recipemanagementapi1.entity.Rating;
import com.example.recipemanagementapi1.entity.User;
import com.example.recipemanagementapi1.repository.IngredientRepository;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.service.IngredientService;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class IngredientServiceImpl implements IngredientService {

    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;


    @Override
    public IngredientResponse createIngredient(IngredientRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ingredient ingredient = Ingredient.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .unit(request.getUnit())
                .user(user)
                .build();

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return IngredientResponse.builder()
                .id(savedIngredient.getId())
                .name(savedIngredient.getName())
                .quantity(savedIngredient.getQuantity())
                .unit(savedIngredient.getUnit())
                .build();
    }



    @Override
    public List<IngredientResponse> getIngredientsByRecipe(Long recipeId) {

        List<Ingredient> ingredients = ingredientRepository.findByRecipesId(recipeId);


        return ingredients.stream()
                .map(ingredient -> new IngredientResponse(
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getQuantity(),
                        ingredient.getUnit()
                ))
                .toList();
    }



    @Override
    public IngredientResponse getIngredientById(Long id) {
        return null;
    }

    @Override
    public IngredientResponse updateIngredient(Long id, IngredientRequest request) {
        return null;
    }

    @Override
    public void deleteIngredient(Long id) {

    }
}
