package com.example.recipemanagementapi1.service.impl;


import com.example.recipemanagementapi1.dto.RatingResponse;
import com.example.recipemanagementapi1.entity.Rating;
import com.example.recipemanagementapi1.entity.Recipe;
import com.example.recipemanagementapi1.entity.User;
import com.example.recipemanagementapi1.repository.RatingRepository;
import com.example.recipemanagementapi1.repository.RecipeRepository;
import com.example.recipemanagementapi1.repository.UserRepository;
import com.example.recipemanagementapi1.service.RatingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {


    private final RatingRepository ratingRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;



    @Override
    public RatingResponse addOrUpdateRating(Long recipeId, Double ratingNumber) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Rating> existing = ratingRepository.findByRecipeIdAndUserEmail(recipeId, user.getEmail());

        Rating rating = ratingRepository.findByRecipeIdAndUserEmail(recipeId, user.getEmail())
                .map(r -> {
                    r.setRatingNumber(ratingNumber);
                    return r;
                })
                .orElseGet(() -> {
                            Rating newRating = new Rating();
                            newRating.setRecipe(recipe);
                            newRating.setUser(user);
                            newRating.setRatingNumber(ratingNumber);
                            return newRating;

                        });
        String fullName = user.getFirstname() + " " + user.getLastname();
        Rating saved = ratingRepository.save(rating);
        return new RatingResponse(saved.getRatingNumber(), recipeId, fullName);
    }

    @Override
    public Double getAverageForRecipe(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Double average = ratingRepository.findAverageRatingNumberByRecipeId(recipeId);
        if (average == null) {
            return 0.0;
        }
        return average;
    }

    @Override
    public List<RatingResponse> getRatingsForRecipe(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        List<Rating> ratings = ratingRepository.findByRecipeId(recipeId);
        return ratings.stream()
                .map(rating -> {
                    String fullName = rating.getUser().getFirstname() + " " + rating.getUser().getLastname();
                    return new RatingResponse(rating.getRatingNumber(), recipeId, fullName);
                })
                .toList();

    }
}
