package org.example.bookrecomendationapp.book;

import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b.id AS id, b.name AS name, b.author AS author, b.description AS description,
        b.pages as pages, b.releaseYear as releaseYear, b.image as image FROM Book b
    """)
    List<BookProjection> findAllBooks();

}
