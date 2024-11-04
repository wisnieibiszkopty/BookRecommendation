package org.example.bookrecomendationapp.opinion.dto;

import jakarta.validation.constraints.NotNull;

public record CreateOpinionDto(@NotNull(message = "Opinion is required") boolean agreed) {
}
