package org.example.bookrecomendationapp;

import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.recomendation.RecommendationRepository;
import org.example.bookrecomendationapp.recomendation.RecommendationService;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @Mock
    private RecommendationBookRepository recommendationBookRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    @Test
    public void testGettingRecommendationsForBook(){

    }

    @Test
    public void testAddingRecommendation(){

    }

    @Test
    public void testUpdatingRecommendation(){

    }

    @Test
    public void testDeletingRecommendation(){

    }

}
