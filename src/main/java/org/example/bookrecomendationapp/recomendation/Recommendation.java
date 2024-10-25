package org.example.bookrecomendationapp.recomendation;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.opinion.RecommendationOpinion;
import org.example.bookrecomendationapp.recommendationBook.RecommendationBook;
import org.example.bookrecomendationapp.user.User;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recommendation {
    @Id
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "recommendation_to_book",
            joinColumns = @JoinColumn(name = "recommendation_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecommendationOpinion> opinions = new HashSet<>();

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecommendationBook> recommendationBooks = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
