package org.example.bookrecomendationapp.recomendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRecommendationDto(
   @NotBlank(message = "Recommendation content is required")
   @NotEmpty(message = "Recommendation content is required")
   @Size(max = 10000, min = 3, message = "Content cannot exceed 10000 characters")
   String content,
   @NotNull(message = "Recommendation book (first) cannot be null")
   Long firstBookId,
   @NotNull(message = "Recommendation book (second) cannot be null")
   Long secondBookId
) {}
