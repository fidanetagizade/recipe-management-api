package com.example.recipemanagementapi1.service;

import com.example.recipemanagementapi1.dto.RatingResponse;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public interface RatingService {

    RatingResponse addOrUpdateRating(Long recipeId, Double ratingNumber);

    Double getAverageForRecipe(Long recipeId);

    List<RatingResponse> getRatingsForRecipe(Long recipeId);
}
