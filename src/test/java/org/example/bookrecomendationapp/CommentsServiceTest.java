package org.example.bookrecomendationapp;

import org.checkerframework.checker.units.qual.C;
import org.example.bookrecomendationapp.book.Book;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.comment.Comment;
import org.example.bookrecomendationapp.comment.CommentRepository;
import org.example.bookrecomendationapp.comment.CommentService;
import org.example.bookrecomendationapp.comment.dto.CommentDto;
import org.example.bookrecomendationapp.comment.dto.CommentProjection;
import org.example.bookrecomendationapp.comment.dto.CreateCommentDto;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.exceptions.CommentNotFoundException;
import org.example.bookrecomendationapp.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentService commentService;

    private User user;
    private CreateCommentDto commentDto;
    private Long bookId = 1L;
    private Long commentId = 1L;

    @BeforeEach
    public void setup() {
        user = User.builder().id(1L).name("Test").email("test@test.com").build();
        commentDto = new CreateCommentDto("Great book!");
    }

    @Test
    public void testGetComments() {
        // Mocking the repository
        when(commentRepository.findAllByBookId(bookId)).thenReturn(List.of());

        // Calling the service method
        var result = commentService.getComments(bookId);

        // Verify the behavior
        assertNotNull(result);
        verify(commentRepository, times(1)).findAllByBookId(bookId);
    }

    @Test
    public void testGetComment_Success() {
        // Mock the repository
        CommentProjection commentProjection = mock(CommentProjection.class);
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.of(commentProjection));

        // Call the service method
        var result = commentService.getComment(bookId, commentId);

        // Verify results
        assertNotNull(result);
        verify(commentRepository, times(1)).findByBookIdAndId(bookId, commentId);
    }

    @Test
    public void testGetComment_NotFound() {
        // Mock repository returning an empty Optional
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.empty());

        // Assert that the BookNotFoundException is thrown
        assertThrows(BookNotFoundException.class, () -> commentService.getComment(bookId, commentId));
    }

    @Test
    public void testAddComment_BookNotFound() {
        // Mocking bookRepository to return empty
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Assert that the BookNotFoundException is thrown
        assertThrows(BookNotFoundException.class, () -> commentService.addComment(bookId, commentDto, user));
    }

    @Test
    public void testAddComment_Success() {
        // Mock bookRepository to return a book
        var book = mock(Book.class);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Mock the saving of the comment
        var savedComment = mock(Comment.class);
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        // Mock ModelMapper to return a CommentDto
        var commentDtoResponse = new CommentDto();
        when(modelMapper.map(savedComment, CommentDto.class)).thenReturn(commentDtoResponse);

        // Call the service method
        CommentDto result = commentService.addComment(bookId, commentDto, user);

        // Verify the behavior
        assertNotNull(result);
        verify(bookRepository, times(1)).findById(bookId);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testUpdateComment_CommentNotFound() {
        // Mock repository returning an empty Optional
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.empty());

        // Assert that the CommentNotFoundException is thrown
        assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(bookId, commentId, user, commentDto));
    }

    @Test
    public void testUpdateComment_Unauthorized() {
        // Mock repository to return a comment
        var existingComment = mock(CommentProjection.class);
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.of(existingComment));

        // Simulate an unauthorized user
        when(existingComment.getUserId()).thenReturn(2L);  // User ID doesn't match

        // Assert that a ResponseStatusException is thrown
        assertThrows(ResponseStatusException.class, () -> commentService.updateComment(bookId, commentId, user, commentDto));
    }

    @Test
    public void testUpdateComment_Success() {
        // Mock repository to return a comment
        var existingComment = mock(CommentProjection.class);
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.of(existingComment));

        // Mock ModelMapper to convert the existing comment
        when(modelMapper.map(existingComment, Comment.class)).thenReturn(new Comment());

        // Mock the save operation and conversion to DTO
        when(commentRepository.save(any(Comment.class))).thenReturn(new Comment());
        when(modelMapper.map(any(Comment.class), eq(CommentDto.class))).thenReturn(new CommentDto());

        // Call the service method
        CommentDto result = commentService.updateComment(bookId, commentId, user, commentDto);

        // Verify the behavior
        assertNotNull(result);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testDeleteComment_CommentNotFound() {
        // Mock repository returning an empty Optional
        when(commentRepository.findByBookIdAndId(bookId, commentId)).thenReturn(Optional.empty());

        // Assert that the CommentNotFoundException is thrown
        assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(bookId, commentId, user));
    }

    @Test
    public void testDeleteComment_Success() {
        // Mock repository to return a comment
        var comment = mock(Comment.class);
        when(commentRepository.findByBookIdAndId(bookId, commentId));

        // Mock the delete operation
        doNothing().when(commentRepository).deleteById(commentId);

        // Call the service method
        commentService.deleteComment(bookId, commentId, user);

        // Verify that the delete method was called
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
