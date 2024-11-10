package org.example.bookrecomendationapp;

import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.shelf.Shelf;
import org.example.bookrecomendationapp.shelf.ShelfRepository;
import org.example.bookrecomendationapp.shelf.ShelfService;
import org.example.bookrecomendationapp.shelf.dto.CreateShelfDto;
import org.example.bookrecomendationapp.user.User;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ShelfServiceTest {

    @Mock
    private ShelfRepository shelfRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private ShelfService shelfService;

    private static Stream<Arguments> provideShelvesData(){
        return Stream.of(
                Arguments.of("shelf", Shelf.builder()
                        .id(1L)
                        .name("test shelf")
                        .user(User.builder().id(4L).build())
                        .build())
        );
    }

    @Test
    public void testAddingShelf(){
        CreateShelfDto shelfDto = new CreateShelfDto("new shelf");
        User user = User.builder().id(123L).build();
        Shelf savedShelf = Shelf.builder()
                .id(1L)
                .name(shelfDto.name())
                .user(user)
                .build();

        when(shelfRepository.save(any(Shelf.class))).thenReturn(savedShelf);

        Shelf result = shelfService.addShelf(shelfDto, user);

        assertNotNull(result);
        assertEquals("new shelf", result.getName());
        assertEquals(user, result.getUser());
        verify(shelfRepository).save(any(Shelf.class));
    }

    @Test
    public void testEditingShelf(){
        // input data
        CreateShelfDto shelfDto = new CreateShelfDto("updatedShelf");
        Long shelfId = 20L;
        Long userId = 4L;

        // mocks
        User user = User.builder().id(4L).build();
        Shelf updatedShelf = Shelf.builder()
                .id(20L)
                .name(shelfDto.name())
                .user(user)
                .build();

        when(shelfRepository.findById(shelfId)).thenReturn(Optional.ofNullable(updatedShelf));
        when(shelfRepository.save(any(Shelf.class))).thenReturn(updatedShelf);

        Shelf result = shelfService.editShelf(shelfDto, shelfId, userId);

        assertNotNull(result);
        assertEquals("updatedShelf", result.getName());
        assertEquals(shelfId, result.getId());
        verify(shelfRepository).save(any(Shelf.class));
    }

    @Test
    public void testThrowingErrorWhileEditing(){
        // input data
        CreateShelfDto shelfDto = new CreateShelfDto("updatedShelf");
        Long shelfId = 20L;
        Long userId = 2L;

        // mocks
        User user = User.builder().id(4L).build();
        Shelf updatedShelf = Shelf.builder()
                .id(20L)
                .name(shelfDto.name())
                .user(user)
                .build();

        when(shelfRepository.findById(shelfId)).thenReturn(Optional.ofNullable(updatedShelf));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
           shelfService.editShelf(shelfDto, shelfId, userId);
        });

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        assertEquals("Shelf doesn't belong to user", ex.getReason());
    }

    @Test
    public void testDeletingShelf(){
        Long shelfId = 5L;
        Long userId = 4L;

        Shelf shelf = Shelf.builder()
            .id(5L)
            .name("test shelf")
            .user(
                User.builder()
                    .id(userId)
                    .build())
            .build();

        when(shelfRepository.findById(shelfId)).thenReturn(Optional.ofNullable(shelf));

        shelfService.deleteShelf(shelfId, userId);

        verify(shelfRepository).delete(any(Shelf.class));
    }

    @Test
    public void testThrowingErrorWhileDeleting(){
        Long shelfId = 5L;
        Long userId = 4L;

        Shelf shelf = Shelf.builder()
            .id(5L)
            .name("test shelf")
            .user(
                User.builder()
                    .id(3L)
                    .build())
            .build();

        when(shelfRepository.findById(shelfId)).thenReturn(Optional.ofNullable(shelf));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            shelfService.deleteShelf(shelfId, userId);
        });

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
        assertEquals("Shelf doesn't belong to user", ex.getReason());
    }

}
