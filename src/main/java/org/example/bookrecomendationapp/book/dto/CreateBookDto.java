package org.example.bookrecomendationapp.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateBookDto(
        @NotBlank(message = "Book name is required")
        @NotEmpty(message = "Book name is required")
        String name,

        @NotBlank(message = "Book author is required")
        @NotEmpty(message = "Book author is required")
        String author,

        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
        String description,

        @Min(value = 1, message = "Pages must be greater than 0")
        int pages,

        @Min(value = -2300, message = "There are no books so old")
        int releaseYear,
        String image,
        Long addedBy) {}
