package org.example.bookrecomendationapp.recomendation;

import org.example.bookrecomendationapp.recomendation.dto.RecommendationFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    List<Recommendation> findAllByBooksId(Long id);

    // TODO change how opinions are counted
    // cannot search for all recommendations for specific books because while creating recommendation
    // table recommendation_to_book is filled with data
    @Query(value = """
        SELECT 
            r.id AS recommendationId,
            r.content AS recommendationContent,
            array_agg(DISTINCT ro.id || ':' || ro.agreed::text) AS opinionsWithAgreed,
            array_agg(DISTINCT b.id) AS bookIds,
            array_agg(DISTINCT b.name) AS bookNames,
            array_agg(DISTINCT b.author) AS bookAuthors
        FROM 
            recommendation AS r
            LEFT JOIN public.recommendation_opinion ro ON r.id = ro.recommendation_id
            JOIN public.recommendation_book rb ON r.id = rb.recommendation_id
            JOIN recommendation_books_mapping rbm ON rb.id = rbm.recommendation_book_id
            JOIN book b ON rbm.book_id = b.id
        WHERE 
            r.id = :id
        GROUP BY 
            r.id, r.content
        """, nativeQuery = true)
    Optional<RecommendationFullProjection> findRecommendationById(@Param("id") Long id);

}
