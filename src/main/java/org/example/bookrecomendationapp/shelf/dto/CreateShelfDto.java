package org.example.bookrecomendationapp.shelf.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateShelfDto(
        @NotBlank(message = "Shelf name is required")
        @NotEmpty(message = "Shelf name is required")
        String name) {}
