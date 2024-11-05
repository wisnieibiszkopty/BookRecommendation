package org.example.bookrecomendationapp.comment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CommentProjection {
    Long getId();
    String getContent();
    LocalDateTime getDate();
    Long getUserId();
    String getUserName();
    Long getBookId();
}
