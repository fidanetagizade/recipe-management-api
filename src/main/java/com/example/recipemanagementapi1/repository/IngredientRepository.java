package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {



    List<Ingredient> findByRecipesId(Long recipeId);

    List<String> findIngredientNamesByRecipeId(Long recipeId);

}
