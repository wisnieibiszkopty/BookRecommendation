package org.example.bookrecomendationapp.comment.dto;

import java.time.LocalDate;

public interface CommentProjection {
    Long getId();
    String getContent();
    LocalDate getDate();
    Long getUserId();
    String getUserName();
    Long getBookId();
}
