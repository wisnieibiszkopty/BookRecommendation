package org.example.bookrecomendationapp.book.dto;

import org.example.bookrecomendationapp.comment.Comment;

import java.util.List;

public class BookViewDto {
    Long id;
    String name;
    String author;
    String description;
    int pages;
    int releaseYear;
    String image;
    private List<Comment> comments;
}
