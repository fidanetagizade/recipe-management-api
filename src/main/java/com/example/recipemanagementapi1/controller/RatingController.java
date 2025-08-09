package com.example.recipemanagementapi1.controller;

import com.example.recipemanagementapi1.dto.RatingRequest;
import com.example.recipemanagementapi1.dto.RatingResponse;
import com.example.recipemanagementapi1.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<RatingResponse> addOrUpdateRating(@RequestBody @Valid RatingRequest request) {
        RatingResponse response = ratingService.addOrUpdateRating(request.getRecipeId(), request.getRatingNumber());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{recipeId}/average")
    public ResponseEntity<Double> getAverageForRecipe(@PathVariable Long recipeId) {
        Double average = ratingService.getAverageForRecipe(recipeId);
        return ResponseEntity.ok(average);
    }

    @GetMapping("/{recipeId}/ratings")
    public ResponseEntity<List<RatingResponse>> getRatingsForRecipe(@PathVariable Long recipeId) {
        List<RatingResponse> responses = ratingService.getRatingsForRecipe(recipeId);
        return ResponseEntity.ok(responses);
    }

}
