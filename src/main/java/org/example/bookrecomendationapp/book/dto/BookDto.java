package org.example.bookrecomendationapp.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Spring model mapper is fr so retarded than it needs class instead of record

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto{
    Long id;
    String name;
    String author;
    String description;
    int pages;
    int releaseYear;
    String image;
}
