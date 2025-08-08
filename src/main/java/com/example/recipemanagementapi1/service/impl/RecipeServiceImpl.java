package com.example.recipemanagementapi1.service.impl;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;

import com.example.recipemanagementapi1.entity.Difficulty;
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

}