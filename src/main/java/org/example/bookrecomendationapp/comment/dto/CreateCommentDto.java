package org.example.bookrecomendationapp.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateCommentDto(
        @NotBlank(message = "Comment content is required")
        @NotEmpty(message = "Comment content is required")
        String comment) {
}
