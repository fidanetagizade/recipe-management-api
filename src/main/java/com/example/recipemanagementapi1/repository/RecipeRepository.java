package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.entity.Difficulty;
import com.example.recipemanagementapi1.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {


    List<Recipe> findByDifficulty(Difficulty level);

    List<Recipe> findByUserId(Long userId);

    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE LOWER(i.name) = LOWER(:ingredientName)")
    List<Recipe> findByIngredientName(@Param("ingredientName") String ingredientName);

}
