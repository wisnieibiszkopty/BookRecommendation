package org.example.bookrecomendationapp.opinion;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.exceptions.OpinionAlreadyExistsException;
import org.example.bookrecomendationapp.exceptions.OpinionNotFoundException;
import org.example.bookrecomendationapp.exceptions.RecommendationNotFoundException;
import org.example.bookrecomendationapp.opinion.dto.CreateOpinionDto;
import org.example.bookrecomendationapp.recomendation.RecommendationRepository;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RecommendationOpinionService {

    private final RecommendationOpinionRepository opinionRepository;
    private final RecommendationRepository recommendationRepository;

    public RecommendationOpinion addOpinion(Long id, CreateOpinionDto opinionDto, User user){
        var opinion = opinionRepository.findRecommendationOpinionByUser(user);
        if(opinion.isPresent()){
            throw new OpinionAlreadyExistsException();
        }

        var recommendation = recommendationRepository
                .findById(id)
                .orElseThrow(RecommendationNotFoundException::new);

        var recommendationOpinion = RecommendationOpinion.builder()
            .agreed(opinionDto.agreed())
            .user(user)
            .recommendation(recommendation)
            .build();

        return opinionRepository.save(recommendationOpinion);
    }

    public void deleteOpinion(Long recommendationId, Long opinionId, User user){
        var opinion = opinionRepository
                .findRecommendationOpinionByIdAndRecommendationId(recommendationId, opinionId)
                .orElseThrow(OpinionNotFoundException::new);

        if(!Objects.equals(opinion.getUser().getId(), user.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Opinion doesn't belong to user");
        }

        opinionRepository.delete(opinion);

    }

}
