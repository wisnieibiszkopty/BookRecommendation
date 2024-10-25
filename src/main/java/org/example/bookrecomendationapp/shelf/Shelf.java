package org.example.bookrecomendationapp.shelf;

import jakarta.persistence.*;
import lombok.Data;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.user.User;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Shelf {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "shelf_book",
            joinColumns = @JoinColumn(name = "shelf_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
