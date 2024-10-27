package org.example.bookrecomendationapp.book;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.dto.BookDto;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    // TODO add sorting books and pagination, books of certain user etc.
    public List<BookDto> getBooks(){
        var books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    // TODO get book recommendations and comments
    public BookDto getBook(Long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
        return modelMapper.map(book, BookDto.class);
    }

    public BookDto createBook(Book book, User user){
        book.setAddedBy(user);
        var savedBook = this.bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBook(Book book){
        var savedBook = this.bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public void deleteBook(Long id){
        this.bookRepository.deleteById(id);
    }

}
