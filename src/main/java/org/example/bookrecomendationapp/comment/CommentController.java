package org.example.bookrecomendationapp.comment;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.bookrecomendationapp.comment.dto.CommentDto;
import org.example.bookrecomendationapp.comment.dto.CommentProjection;
import org.example.bookrecomendationapp.comment.dto.CreateCommentDto;
import org.example.bookrecomendationapp.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/books")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{bookId}/comments")
    public List<CommentProjection> getBooks(@PathVariable Long bookId){
        return commentService.getComments(bookId);
    }

    @GetMapping("/{bookId}/comments/{id}")
    public CommentProjection getBook(@PathVariable Long bookId, @PathVariable Long id){
        return commentService.getComment(bookId, id);
    }

    @PostMapping("/{bookId}/comments")
    public CommentDto addComment(
            @PathVariable Long bookId,
            @Valid @RequestBody CreateCommentDto comment,
            @AuthenticationPrincipal User user){
        return commentService.addComment(bookId, comment, user);
    }

    @PutMapping ("/{bookId}/comments/{id}")
    public CommentDto editComment(
            @PathVariable Long bookId,
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @Valid @RequestBody CreateCommentDto commentDto){
        return commentService.updateComment(bookId, id, user, commentDto);
    }

    @DeleteMapping("/{bookId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long bookId,
            @PathVariable Long id,
            @AuthenticationPrincipal User user){
        commentService.deleteComment(bookId, id, user);
        return ResponseEntity.ok("Deleted comment");
    }

}
