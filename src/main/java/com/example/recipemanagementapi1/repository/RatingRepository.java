package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.dto.RatingResponse;
import com.example.recipemanagementapi1.entity.Rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {


    Optional<Rating> findByRecipeIdAndUserEmail(Long recipeId, String userEmail);

    List<Rating> findByRecipeId(Long recipeId);

    @Query("SELECT AVG(r.ratingNumber) FROM Rating r WHERE r.recipe.id = :recipeId")
    Double findAverageRatingNumberByRecipeId(@Param("recipeId") Long recipeId);



}

