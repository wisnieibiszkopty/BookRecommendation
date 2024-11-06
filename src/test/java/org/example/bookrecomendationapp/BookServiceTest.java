package org.example.bookrecomendationapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.book.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private BookService bookService;


    @Test
    public void testGetBooks(){

    }

    @Test
    public void testGetBookById(){

    }

    @Test
    public void testCreateBook(){

    }

    @Test
    public void testUpdateBook(){

    }

    @Test
    public void testDeleteBook(){

    }

}
