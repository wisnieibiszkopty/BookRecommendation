package org.example.bookrecomendationapp.book;

import org.example.bookrecomendationapp.book.dto.BookFullProjection;
import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("""
        SELECT b.id AS id, b.name AS name, b.author AS author, b.image as image FROM Book b
    """)
    Page<BookProjection> findAllBooks(Pageable pageable);

    @Query("""
         SELECT b.id AS id, b.name AS name, b.author AS author, b.image as image,
         b.description as description, b.releaseYear as releaseYear, b.pages as pages,
         b.addedBy.id as addedBy
         FROM Book b WHERE b.id = :id
     """)
    Optional<BookFullProjection> findBookById(Long id);

    @Query("""  
        select b from Book b
        join b.shelves s
        where b.id = :bookId
        and s.id = :shelfId
    """)
    Optional<Book> findBookByIdAndShelfId(@Param("bookId") Long bookId, @Param("shelfId") Long shelfId);
}
