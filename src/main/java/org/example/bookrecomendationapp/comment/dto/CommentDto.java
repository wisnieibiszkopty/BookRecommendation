package org.example.bookrecomendationapp.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Don't work with edit

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    Long id;
    @NotBlank
    String content;
    @NotBlank
    LocalDate date;
    Long userId;
    Long bookId;
}
