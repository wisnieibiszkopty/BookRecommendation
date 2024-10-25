package org.example.bookrecomendationapp.book;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.recommendationBook.RecommendationBook;
import org.example.bookrecomendationapp.shelf.Shelf;
import org.example.bookrecomendationapp.user.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Book {
    @Id
    private Long id;
    private String name;
    private String author;
    private String description;
    private int pages;
    private Date released;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User addedBy;

    @ManyToMany(mappedBy = "books")
    private Set<Shelf> shelves = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(mappedBy = "books")
    private Set<Recommendation> recommendations = new HashSet<>();

    @ManyToMany(mappedBy = "books")
    private Set<RecommendationBook> recommendationsBooks = new HashSet<>();
}
