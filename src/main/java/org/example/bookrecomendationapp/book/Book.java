package org.example.bookrecomendationapp.book;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.recommendationBook.RecommendationBook;
import org.example.bookrecomendationapp.shelf.Shelf;
import org.example.bookrecomendationapp.user.User;

import java.util.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String description;
    private int pages;
    private int releaseYear;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User addedBy;

    @ManyToMany(mappedBy = "books")
    private List<Shelf> shelves = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<Recommendation> recommendations = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<RecommendationBook> recommendationsBooks = new ArrayList<>();
}
