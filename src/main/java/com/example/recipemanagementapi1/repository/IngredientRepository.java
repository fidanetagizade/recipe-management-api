package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

        List<Ingredient> findByRecipes_Id(Long recipeId);



}
