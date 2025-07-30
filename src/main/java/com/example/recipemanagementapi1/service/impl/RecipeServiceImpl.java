package com.example.recipemanagementapi1.service.impl;

import com.example.recipemanagementapi1.dto.RecipeRequest;
import com.example.recipemanagementapi1.dto.RecipeResponse;

import com.example.recipemanagementapi1.entity.Recipe;
import com.example.recipemanagementapi1.repository.RecipeRepository;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.service.RecipeService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Data
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    RecipeResponse recipeResponse;

    private final UserRepository userRepository;

    @Override
    public RecipeResponse createRecipe(RecipeRequest request) {



        Recipe recipe = Recipe.builder()
                .author(request.getAuthor())
                .title(request.getTitle())
                .description(request.getDescription())
                .time(request.getTime())
                .difficulty(request.getDifficulty())
                .build();

        Recipe savedRecipe = recipeRepository.save(recipe);

        return RecipeResponse.builder()
                .author(savedRecipe.getAuthor())
                .title(savedRecipe.getTitle())
                .description(savedRecipe.getDescription())
                .time(savedRecipe.getTime())
                .difficulty(savedRecipe.getDifficulty())
                .build();
    }

    @Override
    public Set<RecipeResponse> getRecipesById() {
        return null;
    }


}

