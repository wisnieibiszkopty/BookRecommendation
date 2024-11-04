package org.example.bookrecomendationapp.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

// Spring model mapper is fr so retarded than it needs class instead of record

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto{
    @Id
    Long id;

    @NotBlank(message = "Book name is required")
    String name;

    @NotBlank(message = "Book author is required")
    String author;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    String description;

    @Min(value = 1, message = "Pages must be greater than 0")
    int pages;

    @Min(value = -2300, message = "There are no books so old")
    int releaseYear;

    String image;
}
