package org.example.bookrecomendationapp.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.recomendation.dto.RecommendationFullProjection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFullDto {

    Book book;
    RecommendationFullProjection recommendation;

}
