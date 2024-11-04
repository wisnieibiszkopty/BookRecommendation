package org.example.bookrecomendationapp.recomendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EditRecommendationDto(
        @NotBlank(message = "Recommendation content is required")
        @NotEmpty(message = "Recommendation content is required")
        @Size(max = 10000, min = 3, message = "Content cannot exceed 10000 characters")
        String content) {
}
