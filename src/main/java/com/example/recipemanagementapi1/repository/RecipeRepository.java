package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    Optional<Recipe> findRecipeById(Long id);


}
