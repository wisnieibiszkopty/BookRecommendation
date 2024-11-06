package org.example.bookrecomendationapp;

import org.example.bookrecomendationapp.opinion.RecommendationOpinionRepository;
import org.example.bookrecomendationapp.opinion.RecommendationOpinionService;
import org.example.bookrecomendationapp.recomendation.RecommendationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OpinionServiceTest {

    @Mock
    private RecommendationOpinionRepository opinionRepository;

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationOpinionService opinionService;

    @Test
    public void addOpinion(){

    }

    @Test
    public void deleteOpinion(){

    }

}
