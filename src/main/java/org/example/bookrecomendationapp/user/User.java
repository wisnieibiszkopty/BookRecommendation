package org.example.bookrecomendationapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.opinion.RecommendationOpinion;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.shelf.Shelf;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Builder
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Shelf> shelves = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Recommendation> recommendations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
