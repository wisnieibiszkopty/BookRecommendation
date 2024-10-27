package org.example.bookrecomendationapp.book;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.dto.BookDto;
import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.example.bookrecomendationapp.book.dto.CreateBookDto;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.recomendation.RecommendationRepository;
import org.example.bookrecomendationapp.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final RecommendationRepository recommendationRepository;
    private final ModelMapper modelMapper;

    // TODO add sorting books and pagination, books of certain user etc.
    public List<BookProjection> getBooks(){
        return bookRepository.findAllBooks();
    }

    // TODO get book recommendations and comments
    public Book getBook(Long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
        var recommendation = recommendationRepository.findRecommendationById(1L);
        return book;
    }

    public BookDto createBook(CreateBookDto bookDto, User user){
        var book = modelMapper.map(bookDto, Book.class);
        book.setAddedBy(user);
        var savedBook = this.bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBook(BookDto bookDto){
        var book = modelMapper.map(bookDto, Book.class);
        var savedBook = this.bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public void deleteBook(Long id){
        this.bookRepository.deleteById(id);
    }

}
