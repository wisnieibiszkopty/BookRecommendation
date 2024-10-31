package org.example.bookrecomendationapp.book;

import org.example.bookrecomendationapp.book.dto.BookFullProjection;
import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b.id AS id, b.name AS name, b.author AS author, b.image as image FROM Book b
    """)
    List<BookProjection> findAllBooks();

    // More data will be loaded in query with book id
//    @Query("""
//        SELECT b.id AS id, b.name AS name, b.author AS author, b.description AS description,
//        b.pages as pages, b.releaseYear as releaseYear, b.image as image FROM Book b
//    """)
//    List<BookProjection> findAllBooks();

    @Query("""
         SELECT b.id AS id, b.name AS name, b.author AS author, b.image as image,
         b.description as description, b.releaseYear as releaseYear, b.pages as pages,
         b.addedBy.id as addedBy
         FROM Book b WHERE b.id = :id
     """)
    Optional<BookFullProjection> findBookById(Long id);
}
