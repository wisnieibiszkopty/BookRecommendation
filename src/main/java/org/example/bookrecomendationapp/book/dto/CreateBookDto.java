package org.example.bookrecomendationapp.book.dto;

import java.util.Date;

public record CreateBookDto(
        String name,
        String author,
        String description,
        int pages,
        int releaseYear,
        String image) {}
