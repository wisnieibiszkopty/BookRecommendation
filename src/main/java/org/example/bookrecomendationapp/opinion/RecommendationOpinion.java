package org.example.bookrecomendationapp.opinion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookrecomendationapp.recomendation.Recommendation;
import org.example.bookrecomendationapp.user.User;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Opinion is required")
    private boolean agreed;

    @ManyToOne
    @JoinColumn(name = "recommendation_id", nullable = false)
    @JsonBackReference
    private Recommendation recommendation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
