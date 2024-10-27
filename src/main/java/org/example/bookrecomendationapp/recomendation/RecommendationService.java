package org.example.bookrecomendationapp.recomendation;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.exceptions.RecommendationNotFoundException;
import org.example.bookrecomendationapp.recomendation.dto.CreateRecommendationDto;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBook;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBookRepository;
import org.example.bookrecomendationapp.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationBookRepository recommendationBookRepository;
    private final BookRepository bookRepository;

    public Recommendation getRecommendation(Long id){
        return recommendationRepository
                .findById(id)
                .orElseThrow(RecommendationNotFoundException::new);
    }

    public List<Recommendation> getRecommendationsForBook(Long bookId){
        return null;
    }

    @Transactional
    public Optional<Recommendation> addRecommendation(CreateRecommendationDto recommendationDto, User user){
        var firstBook = bookRepository
                .findById(recommendationDto.firstBookId())
                .orElseThrow(BookNotFoundException::new);
        var secondBook = bookRepository
                .findById(recommendationDto.secondBookId())
                .orElseThrow(BookNotFoundException::new);

        var recommendation = Recommendation.builder()
                .content(recommendationDto.content())
                .user(user)
                .build();

        var savedRecommendation = recommendationRepository.save(recommendation);

        var recommendationBooks = RecommendationBook.builder()
                .recommendation(recommendation)
                .books(List.of(firstBook, secondBook))
                .build();

        recommendationBookRepository.save(recommendationBooks);

        return recommendationRepository.findById(savedRecommendation.getId());
    }

    public void editRecommendation(){

    }

    public void deleteRecommendation(){

    }

}
