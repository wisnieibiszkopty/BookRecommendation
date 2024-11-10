package org.example.bookrecomendationapp;

import org.example.bookrecomendationapp.exceptions.OpinionAlreadyExistsException;
import org.example.bookrecomendationapp.exceptions.OpinionNotFoundException;
import org.example.bookrecomendationapp.exceptions.RecommendationNotFoundException;
import org.example.bookrecomendationapp.opinion.RecommendationOpinion;
import org.example.bookrecomendationapp.opinion.RecommendationOpinionRepository;
import org.example.bookrecomendationapp.opinion.RecommendationOpinionService;
import org.example.bookrecomendationapp.opinion.dto.CreateOpinionDto;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.recomendation.RecommendationRepository;
import org.example.bookrecomendationapp.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpinionServiceTest {

    @Mock
    private RecommendationOpinionRepository opinionRepository;

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationOpinionService opinionService;

    private User user;
    private CreateOpinionDto opinionDto;
    private RecommendationOpinion opinion;
    private final Long recommendationId = 1L;
    private final Long opinionId = 1L;

    @BeforeEach
    public void setup() {
        user = User.builder().id(1L).name("Tets").email("test@test").build();
        opinionDto = new CreateOpinionDto(true);

        var recommendation = Recommendation.builder()
                .id(1L).content("This is a recommendation content.")
                .user(user).build();

        opinion = RecommendationOpinion.builder().id(opinionId).user(user).agreed(opinionDto.agreed()).recommendation(recommendation).build();
    }

    @Test
    public void testAddOpinion_OpinionAlreadyExists() {
        // Mock repository to simulate that the user already has an opinion
        when(opinionRepository.findRecommendationOpinionByUser(user)).thenReturn(Optional.of(opinion));

        // Assert that the OpinionAlreadyExistsException is thrown
        assertThrows(OpinionAlreadyExistsException.class, () -> opinionService.addOpinion(recommendationId, opinionDto, user));

        verify(opinionRepository, times(1)).findRecommendationOpinionByUser(user);
    }

    @Test
    public void testAddOpinion_RecommendationNotFound() {
        // Mock repository to simulate that the recommendation is not found
        when(opinionRepository.findRecommendationOpinionByUser(user)).thenReturn(Optional.empty());
        when(recommendationRepository.findById(recommendationId)).thenReturn(Optional.empty());

        // Assert that the RecommendationNotFoundException is thrown
        assertThrows(RecommendationNotFoundException.class, () -> opinionService.addOpinion(recommendationId, opinionDto, user));

        verify(opinionRepository, times(1)).findRecommendationOpinionByUser(user);
        verify(recommendationRepository, times(1)).findById(recommendationId);
    }

    @Test
    public void testDeleteOpinion_OpinionNotFound() {
        // Mock the repository to simulate that no opinion was found for the given IDs
        when(opinionRepository.findRecommendationOpinionByIdAndUserIdAndRecommendationId(opinionId, user.getId(), recommendationId))
                .thenReturn(Optional.empty());

        // Assert that the OpinionNotFoundException is thrown
        assertThrows(OpinionNotFoundException.class, () -> opinionService.deleteOpinion(recommendationId, opinionId, user.getId()));

        verify(opinionRepository, times(1))
                .findRecommendationOpinionByIdAndUserIdAndRecommendationId(opinionId, user.getId(), recommendationId);
    }

    @Test
    public void testDeleteOpinion_Success() {
        // Mock the repository to simulate that the opinion exists
        when(opinionRepository.findRecommendationOpinionByIdAndUserIdAndRecommendationId(opinionId, user.getId(), recommendationId))
                .thenReturn(Optional.of(opinion));

        // Mock the delete operation
        doNothing().when(opinionRepository).delete(opinion);

        // Perform the operation
        opinionService.deleteOpinion(recommendationId, opinionId, user.getId());

        // Verify that the delete method was called once
        verify(opinionRepository, times(1)).delete(opinion);
    }

    @Test
    public void testDeleteOpinion_Unauthorized() {
        // Mock the repository to simulate that the opinion exists but belongs to a different user
        when(opinionRepository.findRecommendationOpinionByIdAndUserIdAndRecommendationId(opinionId, 999L, recommendationId))
                .thenReturn(Optional.of(opinion));

        // Assert that a ResponseStatusException with CONFLICT status is thrown
        assertThrows(ResponseStatusException.class, () -> opinionService.deleteOpinion(recommendationId, opinionId, 999L));

        verify(opinionRepository, times(1))
                .findRecommendationOpinionByIdAndUserIdAndRecommendationId(opinionId, 999L, recommendationId);
    }

}
