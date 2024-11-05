package org.example.bookrecomendationapp.book;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.book.dto.BookDto;
import org.example.bookrecomendationapp.book.dto.BookFullProjection;
import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.example.bookrecomendationapp.book.dto.CreateBookDto;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public Page<BookProjection> getBooks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAllBooks(pageable);
    }

    public BookFullProjection getBook(Long id){
        return bookRepository.findBookById(id).orElseThrow(BookNotFoundException::new);
    }

    public BookDto createBook(CreateBookDto bookDto, User user){
        var book = modelMapper.map(bookDto, Book.class);
        book.setAddedBy(user);
        var savedBook = this.bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBook(Long bookId, BookDto bookDto, Long userId){
        var existingBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        if(existingBook.getAddedBy().getId() != userId){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book doesn't belong to user");
        }

        var book = modelMapper.map(bookDto, Book.class);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setImage(book.getImage());
        existingBook.setDescription(book.getDescription());
        existingBook.setName(book.getName());
        existingBook.setPages(book.getPages());
        existingBook.setReleaseYear(book.getReleaseYear());
        existingBook.setId(bookId);
        var savedBook = this.bookRepository.save(existingBook);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public void deleteBook(Long bookId, Long userId){
        var book = bookRepository.findBookById(bookId).orElseThrow(BookNotFoundException::new);
        if(book.getAddedBy() != userId){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book doesn't belong to user");
        }

        this.bookRepository.deleteById(bookId);
    }

}
