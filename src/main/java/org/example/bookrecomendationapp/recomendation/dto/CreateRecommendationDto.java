package org.example.bookrecomendationapp.recomendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRecommendationDto(
   @Size(max = 10000, min = 3, message = "Content cannot exceed 10000 characters")
   String content,
   @NotNull
   Long firstBookId,
   @NotNull
   Long secondBookId
) {}
