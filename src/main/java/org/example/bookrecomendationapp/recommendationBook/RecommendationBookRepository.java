package org.example.bookrecomendationapp.recommendationBook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationBookRepository extends JpaRepository<RecommendationBook, Long> {
}
