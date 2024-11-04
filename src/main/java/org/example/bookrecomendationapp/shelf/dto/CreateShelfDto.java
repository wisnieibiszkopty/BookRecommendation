package org.example.bookrecomendationapp.shelf.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateShelfDto(@NotBlank(message = "Shelf name is required") String name) {}
