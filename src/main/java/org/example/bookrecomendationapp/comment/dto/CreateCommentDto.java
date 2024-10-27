package org.example.bookrecomendationapp.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentDto(@NotBlank String comment) {
}
