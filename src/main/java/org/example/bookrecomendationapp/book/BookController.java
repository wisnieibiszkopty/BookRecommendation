package org.example.bookrecomendationapp.book;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.book.dto.BookDto;
import org.example.bookrecomendationapp.book.dto.BookFullProjection;
import org.example.bookrecomendationapp.book.dto.BookProjection;
import org.example.bookrecomendationapp.book.dto.CreateBookDto;
import org.example.bookrecomendationapp.user.User;
import org.springframework.data.domain.Page;
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
    public Page<BookProjection> getBooks(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "9") int size){
        return bookService.getBooks(page, size);
    }

    @GetMapping("/{id}")
    public BookFullProjection getBook(@PathVariable Long id){
        return bookService.getBook(id);
    }

    @PostMapping
    public BookDto createBook(@AuthenticationPrincipal User user, @Valid @RequestBody CreateBookDto book){
        return bookService.createBook(book, user);
    }

    @PutMapping("/{bookId}")
    public BookDto editBook(
            @PathVariable Long bookId,
            @Valid @RequestBody BookDto book,
            @AuthenticationPrincipal User user){
        return bookService.updateBook(bookId, book, user.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(
            @PathVariable Long id, @AuthenticationPrincipal User user){
        bookService.deleteBook(id, user.getId());
        return ResponseEntity.ok().build();
    }

}
