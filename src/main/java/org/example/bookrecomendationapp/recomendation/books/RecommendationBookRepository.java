package org.example.bookrecomendationapp.recomendation.books;

import org.example.bookrecomendationapp.recomendation.books.RecommendationBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationBookRepository extends JpaRepository<RecommendationBook, Long> {
}
