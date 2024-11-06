package org.example.bookrecomendationapp;

import org.checkerframework.checker.units.qual.C;
import org.example.bookrecomendationapp.book.BookRepository;
import org.example.bookrecomendationapp.comment.CommentRepository;
import org.example.bookrecomendationapp.comment.CommentService;
import org.example.bookrecomendationapp.comment.dto.CreateCommentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

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

    @Test
    public void testGettingComments(){

    }

    @Test
    public void testGettingComment(){

    }

    @Test
    public void testAddingComment(){
        CreateCommentDto commentDto = new CreateCommentDto("test comment");
        Long bookId = 100L;

        //when(bookRepository.findById())
    }

    @Test
    public void testUpdatingComment(){

    }

    @Test
    public void testDeletingComment(){

    }
}
