package org.example.bookrecomendationapp.opinion;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.opinion.dto.CreateOpinionDto;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/recommendations")
public class RecommendationOpinionController {

    private final RecommendationOpinionService opinionService;

    @PostMapping("/{recommendationId}/opinions")
    public RecommendationOpinion addOpinion(
            @PathVariable Long recommendationId,
            @RequestBody CreateOpinionDto opinionDto,
            @AuthenticationPrincipal User user){
        return opinionService.addOpinion(recommendationId, opinionDto, user);
    }

    @DeleteMapping("/{recommendationId}/opinions/{opinionId}")
    public ResponseEntity<String> deleteOpinion(
            @PathVariable Long recommendationId,
            @PathVariable Long opinionId,
            @AuthenticationPrincipal User user){
        opinionService.deleteOpinion(recommendationId, opinionId, user.getId());
        return ResponseEntity.ok("Opinion deleted");
    }

}
