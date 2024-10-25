package org.example.bookrecomendationapp.opinion;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.user.User;

@Data
@Entity
public class RecommendationOpinion {
    @Id
    private Long id;

    private boolean agreed;

    @ManyToOne
    @JoinColumn(name = "recommendation_id", nullable = false)
    private Recommendation recommendation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
