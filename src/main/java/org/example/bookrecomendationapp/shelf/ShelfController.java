package org.example.bookrecomendationapp.shelf;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.shelf.dto.CreateShelfDto;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("api/shelves")
@AllArgsConstructor
public class ShelfController {

    private ShelfService shelfService;

    @GetMapping("/{shelfId}")
    public Shelf getShelf(@PathVariable Long shelfId){
        return shelfService.getShelf(shelfId);
    }

    @GetMapping("/user/{userId}")
    public List<Shelf> getUserShelves(@PathVariable Long userId){
        return shelfService.getShelves(userId);
    }

    @PostMapping
    public Shelf addShelf(@Valid @RequestBody CreateShelfDto shelf, @AuthenticationPrincipal User user){
        return shelfService.addShelf(shelf, user);
    }

    @PostMapping("/{shelfId}/book/{bookId}")
    public Shelf addBookToShelf(
            @PathVariable Long shelfId,
            @PathVariable Long bookId,
            @AuthenticationPrincipal User user){
        return shelfService.addBookToShelf(shelfId, bookId, user.getId());
    }

    @PutMapping("/{shelfId}")
    public Shelf editShelf(
            @PathVariable Long shelfId,
            @RequestBody CreateShelfDto shelfDto,
            @AuthenticationPrincipal User user){
        return shelfService.editShelf(shelfDto, shelfId, user.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShelf(@PathVariable Long id, @AuthenticationPrincipal User user){
        shelfService.deleteShelf(id, user.getId());
        return ResponseEntity.ok(Map.of("message", "Deleted shelf"));
    }

    // TODO add deleting book from shelf

}
