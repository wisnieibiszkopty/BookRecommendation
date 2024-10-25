package org.example.bookrecomendationapp.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.user.User;

import java.util.Date;

@Data
@Entity
public class Comment {
    @Id
    private Long id;
    private String content;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
