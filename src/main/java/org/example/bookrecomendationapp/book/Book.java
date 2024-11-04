package org.example.bookrecomendationapp.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBook;
import org.example.bookrecomendationapp.shelf.Shelf;
import org.example.bookrecomendationapp.user.User;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book name is required")
    private String name;

    @NotBlank(message = "Book author is required")
    private String author;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Min(value = 1, message = "Pages must be greater than 0")
    private int pages;

    // Epic of Gilgamesh
    @Min(value = -2300, message = "There are no books so old")
    private int releaseYear;

    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User addedBy;

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    @JsonIgnore
    private List<Shelf> shelves = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    @JsonManagedReference
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    @JsonIgnore
    private List<Recommendation> recommendations = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    @JsonBackReference
    @JsonIgnore
    private List<RecommendationBook> recommendationsBooks = new ArrayList<>();
}
