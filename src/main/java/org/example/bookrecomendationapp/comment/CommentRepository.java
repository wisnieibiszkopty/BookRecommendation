package org.example.bookrecomendationapp.comment;

import org.example.bookrecomendationapp.comment.dto.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // TODO add comments pagination in future

    @Query("""
        SELECT c.id AS id, c.content AS content, c.date AS date, c.book.id AS bookId, u.name AS userName, u.id AS userId
        FROM Comment c JOIN c.user u WHERE c.book.id = :bookId
    """)
    List<CommentProjection> findAllByBookId(@Param("bookId") Long bookId);

    @Query(""" 
            SELECT c.id AS id, c.content AS content, c.date AS date, c.book.id AS bookId, u.name AS userName, u.id AS userId
            FROM Comment c JOIN c.user u WHERE c.book.id = :bookId AND c.id = :id
            """)
    Optional<CommentProjection> findByBookIdAndId(@Param("bookId") Long bookId, @Param("id") Long id);

}
