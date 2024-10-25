package org.example.bookrecomendationapp.recommendationBook;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.recomendation.Recommendation;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class RecommendationBook {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recommendation_id", nullable = false)
    private Recommendation recommendation;

    @ManyToMany
    @JoinTable(
            name = "recommendation_books_mapping",
            joinColumns = @JoinColumn(name = "recommendation_book_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();
}
