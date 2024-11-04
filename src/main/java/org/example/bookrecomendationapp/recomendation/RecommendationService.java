package org.example.bookrecomendationapp.recomendation;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.exceptions.RecommendationNotFoundException;
import org.example.bookrecomendationapp.recomendation.dto.CreateRecommendationDto;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBook;
import org.example.bookrecomendationapp.recomendation.books.RecommendationBookRepository;
import org.example.bookrecomendationapp.recomendation.dto.RecommendationFullProjection;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final RecommendationBookRepository recommendationBookRepository;
    private final BookRepository bookRepository;

    public RecommendationFullProjection getRecommendation(Long id){
        return recommendationRepository
                .findRecommendationById(id)
                .orElseThrow(RecommendationNotFoundException::new);
    }

    // don't work
    public List<Recommendation> getRecommendationsForBook(Long bookId){
        return recommendationRepository.findAllByBooksId(bookId);
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
                .books(List.of(firstBook, secondBook))
                .build();

        var savedRecommendation = recommendationRepository.save(recommendation);

        var recommendationBooks = RecommendationBook.builder()
                .recommendation(recommendation)
                .books(List.of(firstBook, secondBook))
                .build();

        recommendationBookRepository.save(recommendationBooks);

        return recommendationRepository.findById(savedRecommendation.getId());
    }

    public Recommendation editRecommendation(CreateRecommendationDto recommendationDto, Long recommendationId, User user){
        var recommendation = recommendationRepository
                .findById(recommendationId)
                .orElseThrow(RecommendationNotFoundException::new);

        if(!Objects.equals(recommendation.getUser().getId(), user.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Recommendation doesn't belong to user");
        }

        recommendation.setContent(recommendationDto.content());
        return recommendationRepository.save(recommendation);
    }

    public void deleteRecommendation(Long id, User user){
        var recommendation = recommendationRepository
                .findById(id)
                .orElseThrow(RecommendationNotFoundException::new);

        if(!Objects.equals(recommendation.getUser().getId(), user.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Recommendation doesn't belong to user");
        }

        recommendationRepository.delete(recommendation);
    }

}
