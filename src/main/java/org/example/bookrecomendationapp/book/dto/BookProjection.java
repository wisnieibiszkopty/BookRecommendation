package org.example.bookrecomendationapp.book.dto;

public interface BookProjection {
    Long getId();
    String getName();
    String getAuthor();
    String getDescription();
    int getPages();
    int getReleaseYear();
    String getImage();
}
