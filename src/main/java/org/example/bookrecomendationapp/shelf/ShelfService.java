package org.example.bookrecomendationapp.shelf;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.exceptions.BookAlreadyInShelfException;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.exceptions.ShelfNotFoundException;
import org.example.bookrecomendationapp.shelf.dto.CreateShelfDto;
import org.example.bookrecomendationapp.shelf.dto.ShelfProjection;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class ShelfService {

    private final ShelfRepository shelfRepository;
    private final BookRepository bookRepository;

    // idea -> make private shelves
    public Shelf getShelf(Long id){
        return shelfRepository.findById(id).orElseThrow(ShelfNotFoundException::new);
    }

    public List<ShelfProjection> getShelves(Long userId){
        return shelfRepository.findShelfByUserId(userId);
    }

    public Shelf addShelf(CreateShelfDto shelfDto, User user){
        var shelf = Shelf.builder()
                .name(shelfDto.name())
                .user(user)
                .build();

        return shelfRepository.save(shelf);
    }

    // check if shelf like that already exists
    public Shelf addBookToShelf(Long shelfId, Long bookId, Long userId){
        var book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        var shelf = shelfRepository.findById(shelfId).orElseThrow(ShelfNotFoundException::new);

        checkIfShelfBelongToUser(shelf.getUser().getId(), userId);

        // check if book is already added to shelf
        if (shelf.getBooks().contains(book)) {
            throw new BookAlreadyInShelfException();
        }

        shelf.addBook(book);
        return shelfRepository.save(shelf);
    }

    public Shelf editShelf(CreateShelfDto shelfDto, Long shelfId, Long userId){
        var shelf = shelfRepository.findById(shelfId).orElseThrow(ShelfNotFoundException::new);

        checkIfShelfBelongToUser(shelf.getUser().getId(), userId);

        shelf.setName(shelfDto.name());
        return shelfRepository.save(shelf);
    }

    public void deleteShelf(Long shelfId, Long userId){
        var shelf = shelfRepository.findById(shelfId).orElseThrow(ShelfNotFoundException::new);
        checkIfShelfBelongToUser(shelf.getUser().getId(), userId);

        shelfRepository.delete(shelf);
    }

    public void deleteBookFromShelf(Long shelfId, Long bookId, Long userId){
        var shelf = shelfRepository.findById(shelfId).orElseThrow(ShelfNotFoundException::new);
        checkIfShelfBelongToUser(shelf.getUser().getId(), userId);
        var book = bookRepository.findBookByIdAndShelfId(bookId, shelfId);

        if(book.isPresent()){
            shelf.getBooks().remove(book.get());
            shelfRepository.save(shelf);
        } else {
            throw new BookNotFoundException();
        }
    }

    private void checkIfShelfBelongToUser(Long shelfUserId, Long userId){
        if(!Objects.equals(shelfUserId, userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shelf doesn't belong to user");
        }
    }

}
