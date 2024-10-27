package org.example.bookrecomendationapp.comment;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.comment.dto.CommentDto;
import org.example.bookrecomendationapp.comment.dto.CommentProjection;
import org.example.bookrecomendationapp.comment.dto.CreateCommentDto;
import org.example.bookrecomendationapp.exceptions.BookNotFoundException;
import org.example.bookrecomendationapp.exceptions.CommentNotFoundException;
import org.example.bookrecomendationapp.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    public List<CommentProjection> getComments(Long bookId){
        return commentRepository.findAllByBookId(bookId);
    }

    public CommentProjection getComment(Long bookId, Long id){
        return commentRepository.findByBookIdAndId(bookId, id).orElseThrow(BookNotFoundException::new);
    }

    // how this orm is even supposed to work?????
    // TODO optimize
    public CommentDto addComment(Long bookId, CreateCommentDto commentDto, User user){
        var book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        var comment = Comment.builder()
                .content(commentDto.comment())
                .date(LocalDate.now())
                .book(book)
                .user(user)
                .build();

        var savedComment = commentRepository.save(comment);

        var commentResponse = modelMapper.map(savedComment, CommentDto.class);
        commentResponse.setUserId(savedComment.getUser().getId());
        commentResponse.setBookId(savedComment.getBook().getId());
        return commentResponse;
    }

    // TODO optimize
    public CommentDto updateComment(Long bookId, Long id, User user, CreateCommentDto commentDto){
        var commentProjection = commentRepository.findByBookIdAndId(bookId, id).orElseThrow(() -> new CommentNotFoundException());

        if(!Objects.equals(commentProjection.getUserId(), user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment don't belong to this user");
        }

        var savedComment = modelMapper.map(commentProjection, Comment.class);
        savedComment.setContent(commentDto.comment());

        var commentResponse = modelMapper.map(commentRepository.save(savedComment), CommentDto.class);
        commentResponse.setUserId(savedComment.getUser().getId());
        commentResponse.setBookId(savedComment.getBook().getId());
        return commentResponse;
    }

    public void deleteComment(Long bookId, Long id, User user){
        var comment = commentRepository.findByBookIdAndId(bookId, id).orElseThrow(() -> new CommentNotFoundException());

        if(!Objects.equals(comment.getUserId(), user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment don't belong to this user");
        }

        commentRepository.deleteById(comment.getId());
    }

}
