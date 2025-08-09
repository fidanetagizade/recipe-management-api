package com.example.recipemanagementapi1.service.impl;

import com.example.recipemanagementapi1.dto.IngredientRequest;
import com.example.recipemanagementapi1.dto.IngredientResponse;
import com.example.recipemanagementapi1.entity.Ingredient;
import com.example.recipemanagementapi1.entity.User;
import com.example.recipemanagementapi1.repository.IngredientRepository;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.service.IngredientService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
@RequiredArgsConstructor
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

        return mapToResponse(savedIngredient);
    }

    @Override
    public List<IngredientResponse> getIngredientsByRecipe(Long recipeId) {
        List<Ingredient> ingredients = ingredientRepository.findByRecipes_Id(recipeId);
        return ingredients.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public IngredientResponse getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
        return mapToResponse(ingredient);
    }

    @Override
    public IngredientResponse updateIngredient(Long id, IngredientRequest request) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));

        ingredient.setName(request.getName());
        ingredient.setQuantity(request.getQuantity());
        ingredient.setUnit(request.getUnit());

        Ingredient updatedIngredient = ingredientRepository.save(ingredient);
        return mapToResponse(updatedIngredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new RuntimeException("Ingredient not found");
        }
        ingredientRepository.deleteById(id);
    }

    private IngredientResponse mapToResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .quantity(ingredient.getQuantity())
                .unit(ingredient.getUnit())
                .build();
    }
}
