package org.example.bookrecomendationapp.book;

import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.dto.CreateBookDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    // TODO add sorting books and pagination, books of certain user etc.
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    // TODO get book recommendations and comments
    public Book getBook(Long id){
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    // TODO add user which is adding book
    public Book createBook(Book book){
        return this.bookRepository.save(book);
    }

    public Book updateBook(Book book){
        return this.bookRepository.save(book);
    }

    public void deleteBook(Long id){
        this.bookRepository.deleteById(id);
    }

}
