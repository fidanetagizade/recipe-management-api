package com.example.recipemanagementapi1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RatingResponse {

    private Double ratingNumber;

    private Long recipeId;

    private String author;
}
