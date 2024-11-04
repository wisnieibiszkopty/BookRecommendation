package org.example.bookrecomendationapp.recomendation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.recomendation.dto.CreateRecommendationDto;
import org.example.bookrecomendationapp.recomendation.dto.EditRecommendationDto;
import org.example.bookrecomendationapp.recomendation.dto.RecommendationFullProjection;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/recommendations")
@AllArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{id}")
    public RecommendationFullProjection getRecommendation(@PathVariable Long id){
        return recommendationService.getRecommendation(id);
    }

    @GetMapping("/book/{bookId}")
    public List<Recommendation> getRecommendationsForBook(@PathVariable Long bookId){
        return recommendationService.getRecommendationsForBook(bookId);
    }

    @PostMapping
    public Optional<Recommendation> addRecommendation(
            @Valid @RequestBody CreateRecommendationDto recommendationDto,
            @AuthenticationPrincipal User user){
        return recommendationService.addRecommendation(recommendationDto, user);
    }

    @PutMapping("/{id}")
    public Recommendation editRecommendation(
        @PathVariable Long id,
        @Valid @RequestBody EditRecommendationDto recommendationDto,
        @AuthenticationPrincipal User user){
        return recommendationService.editRecommendation(recommendationDto, id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecommendation(
            @PathVariable Long id,
            @AuthenticationPrincipal User user){
        recommendationService.deleteRecommendation(id, user);
        return ResponseEntity.ok("Deleted recommendation");
    }
}
