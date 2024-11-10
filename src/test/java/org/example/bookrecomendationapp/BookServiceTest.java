package org.example.bookrecomendationapp;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.example.bookrecomendationapp.book.*;
import org.example.bookrecomendationapp.book.dto.*;
import org.example.bookrecomendationapp.user.User;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookService bookService;

    private User user;
    private Book book;
    private BookDto bookDto;
    private CreateBookDto createBookDto;
    private Pageable pageable;
    private Page<BookProjection> bookProjectionPage;

    @BeforeEach
    public void setup() {
        user = User.builder().id(1L).name("Tets").email("test@test").build();
        createBookDto = new CreateBookDto("Book Name", "Author", "Description", 300, 2024, "image_url", null);
        bookDto = new BookDto(1L, "Book Name", "Author", "Description", 300, 2024, "image_url");
        book = Book.builder().id(1L).name("Book name").author("Author").description("Description").pages(300).releaseYear(2024).addedBy(user).build();
        pageable = PageRequest.of(0, 10);
        bookProjectionPage = mock(Page.class);
    }

    @Test
    public void testGetBooks() {
        when(bookRepository.findAllBooks(pageable)).thenReturn(bookProjectionPage);
        Page<BookProjection> result = bookService.getBooks(0, 10);
        assertNotNull(result);
        verify(bookRepository, times(1)).findAllBooks(pageable);
    }

    @Test
    public void testGetBookNotFound() {
        when(bookRepository.findBookById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.getBook(1L));
    }

    @Test
    public void testCreateBook() {
        when(modelMapper.map(createBookDto, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);

        BookDto result = bookService.createBook(createBookDto, user);
        assertNotNull(result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        BookDto result = bookService.updateBook(1L, bookDto, 1L);
        assertNotNull(result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBookUnauthorized() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(book));
        assertThrows(ResponseStatusException.class, () -> bookService.updateBook(1L, bookDto, 2L));
    }

    @Test
    public void testDeleteBookNotFound() {
        when(bookRepository.findBookById(1L)).thenReturn(java.util.Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(1L, 1L));
    }
}