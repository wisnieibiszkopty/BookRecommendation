package org.example.bookrecomendationapp.opinion;

import org.example.bookrecomendationapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendationOpinionRepository extends JpaRepository<RecommendationOpinion, Long> {

    Optional<RecommendationOpinion> findRecommendationOpinionByUser(User user);

    Optional<RecommendationOpinion> findRecommendationOpinionByIdAndRecommendationId(Long opinionId, Long recommendationId);

}
