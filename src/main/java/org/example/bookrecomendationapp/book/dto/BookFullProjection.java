package org.example.bookrecomendationapp.book.dto;

public interface BookFullProjection {
    Long getId();
    String getName();
    String getAuthor();
    String getDescription();
    String getImage();
    int getPages();
    int getReleaseYear();
    Long getAddedBy();

}
