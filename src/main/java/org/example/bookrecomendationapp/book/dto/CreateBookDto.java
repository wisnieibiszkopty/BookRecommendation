package org.example.bookrecomendationapp.book.dto;

public record CreateBookDto(
        String name,
        String author,
        String description,
        int pages,
        int releaseYear,
        String image) {}
