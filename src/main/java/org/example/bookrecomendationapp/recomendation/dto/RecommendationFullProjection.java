package org.example.bookrecomendationapp.recomendation.dto;

import org.example.bookrecomendationapp.opinion.RecommendationOpinion;
import org.example.bookrecomendationapp.user.User;

import java.util.List;

public interface RecommendationFullProjection {
    Long getRecommendationId();
    String getRecommendationContent();
    List<String> getOpinionsWithAgreed();
    List<Long> getBookIds();
    List<String> getBookNames();
    List<String> getBookAuthors();
}
