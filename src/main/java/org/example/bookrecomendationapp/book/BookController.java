package org.example.bookrecomendationapp.book;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.dto.BookDto;
import org.example.bookrecomendationapp.book.dto.BookViewDto;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping
    public BookDto createBook(@AuthenticationPrincipal User user, @Valid @RequestBody Book book){
        return bookService.createBook(book, user);
    }

    @PutMapping()
    public BookDto editBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
