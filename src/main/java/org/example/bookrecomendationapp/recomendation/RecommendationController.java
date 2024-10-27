package org.example.bookrecomendationapp.recomendation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.recomendation.dto.CreateRecommendationDto;
import org.example.bookrecomendationapp.user.User;
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
    public Recommendation getRecommendation(@PathVariable Long id){
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

}
