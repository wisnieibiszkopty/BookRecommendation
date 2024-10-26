package org.example.bookrecomendationapp.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.opinion.RecommendationOpinion;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.shelf.Shelf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shelf> shelves = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Recommendation> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<RecommendationOpinion> opinions = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        book.setAddedBy(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setAddedBy(null);
    }

    public void addShelf(Shelf shelf) {
        shelves.add(shelf);
        shelf.setUser(this);
    }

    public void removeShelf(Shelf shelf) {
        shelves.remove(shelf);
        shelf.setUser(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }

    public void addRecommendation(Shelf shelf) {
        shelves.add(shelf);
        shelf.setUser(this);
    }

    public void removeRecommendation(Recommendation recommendation) {
        recommendations.remove(recommendation);
        recommendation.setUser(null);
    }

    public void addRecommendationOpinion(RecommendationOpinion opinion) {
        opinions.add(opinion);
        opinion.setUser(this);
    }

    public void removeRecommendationOpinion(RecommendationOpinion opinion) {
        opinions.remove(opinion);
        opinion.setUser(null);
    }
}
