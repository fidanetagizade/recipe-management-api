package com.example.recipemanagementapi1.service.impl;

import com.example.recipemanagementapi1.dto.IngredientResponse;
import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;

import com.example.recipemanagementapi1.entity.Difficulty;
import com.example.recipemanagementapi1.entity.Ingredient;
import com.example.recipemanagementapi1.entity.Recipe;
import com.example.recipemanagementapi1.entity.User;
import com.example.recipemanagementapi1.repository.RecipeRepository;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.service.RecipeService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@Data
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    RecipeResponse recipeResponse;

    private final UserRepository userRepository;

    @Override
    public RecipeResponse createRecipe(RecipeRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Recipe recipe = Recipe.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .time(request.getTime())
                .difficulty(request.getDifficulty())
                .user(user)
                .build();

        if (request.getIngredients() != null) {
            List<Ingredient> ingredients = request.getIngredients().stream()
                    .map(ing -> Ingredient.builder()
                            .name(ing.getName())
                            .quantity(ing.getQuantity())
                            .unit(ing.getUnit())
                            .build())
                    .toList();

            recipe.setIngredients(ingredients);

            ingredients.forEach(ingredient -> {
                if (ingredient.getRecipes() == null) {
                    ingredient.setRecipes(new ArrayList<>());
                }
                ingredient.getRecipes().add(recipe);
            });
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeResponse.builder()
                .id(savedRecipe.getId())
                .author(user.getFirstname() + " " + user.getLastname())
                .title(savedRecipe.getTitle())
                .description(savedRecipe.getDescription())
                .time(savedRecipe.getTime())
                .difficulty(savedRecipe.getDifficulty())
                .ingredients(savedRecipe.getIngredients().stream()
                        .map(ing -> IngredientResponse.builder()
                                .id(ing.getId())
                                .name(ing.getName())
                                .quantity(ing.getQuantity())
                                .unit(ing.getUnit())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public Set<RecipeResponse> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();

        return recipes.stream()
                .map(recipe -> RecipeResponse.builder()
                        .id(recipe.getId())
                        .author(recipe.getUser().getFirstname() + " " + recipe.getUser().getLastname())
                        .title(recipe.getTitle())
                        .description(recipe.getDescription())
                        .time(recipe.getTime())
                        .difficulty(recipe.getDifficulty())
                        .ingredients(
                                recipe.getIngredients().stream()
                                        .map(ing -> IngredientResponse.builder()
                                                .id(ing.getId())
                                                .name(ing.getName())
                                                .quantity(ing.getQuantity())
                                                .unit(ing.getUnit())
                                                .build())
                                        .toList()
                        )
                        .build())
                .collect(Collectors.toSet());
    }


    @Override
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);

    }
    @Override
    public RecipeResponse updateRecipe(Long id, RecipeRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));


        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setTime(request.getTime());
        recipe.setDifficulty(request.getDifficulty());

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeResponse.builder()
                .id(savedRecipe.getId())
                .author(user.getFirstname() + " " + user.getLastname())
                .title(savedRecipe.getTitle())
                .description(savedRecipe.getDescription())
                .time(savedRecipe.getTime())
                .difficulty(savedRecipe.getDifficulty())
                .build();
    }

    @Override
    public Set<RecipeResponse> getRecipesByDifficulty(Difficulty level) {
        List<Recipe> recipes = recipeRepository.findByDifficulty(level);

        return recipes.stream()
                .map(recipe -> RecipeResponse.builder()
                        .id(recipe.getId())
                        .author(recipe.getUser().getFirstname() + " " + recipe.getUser().getLastname())
                        .title(recipe.getTitle())
                        .description(recipe.getDescription())
                        .time(recipe.getTime())
                        .difficulty(recipe.getDifficulty())
                        .build())
                .collect(Collectors.toSet());
    }

    @Override
    public List<RecipeResponse> getRecipesByUserId() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Recipe> recipes = recipeRepository.findByUserId(user.getId());

        return recipes.stream()
                .map(recipe -> RecipeResponse.builder()
                        .id(recipe.getId())
                        .author(recipe.getUser().getFirstname() + " " + recipe.getUser().getLastname())
                        .title(recipe.getTitle())
                        .description(recipe.getDescription())
                        .time(recipe.getTime())
                        .difficulty(recipe.getDifficulty())
                        .build())
                .collect(toList());

    }
    @Override
    public List<RecipeResponse> getRecipesByIngredient(String ingredientName) {
        List<Recipe> recipes = recipeRepository.findByIngredientName(ingredientName);

        return recipes.stream()
                .map(recipe -> RecipeResponse.builder()
                        .id(recipe.getId())
                        .author(recipe.getUser().getFirstname() + " " + recipe.getUser().getLastname())
                        .title(recipe.getTitle())
                        .description(recipe.getDescription())
                        .time(recipe.getTime())
                        .difficulty(recipe.getDifficulty())
                        .ingredients(recipe.getIngredients().stream()
                                .map(ing -> IngredientResponse.builder()
                                        .id(ing.getId())
                                        .name(ing.getName())
                                        .quantity(ing.getQuantity())
                                        .unit(ing.getUnit())
                                        .build())
                                .toList())
                        .build())
                .collect(Collectors.toList());
    }


}