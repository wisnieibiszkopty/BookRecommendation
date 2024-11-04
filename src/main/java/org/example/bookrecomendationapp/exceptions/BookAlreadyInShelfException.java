package org.example.bookrecomendationapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Book already added to shelf")
public class BookAlreadyInShelfException extends RuntimeException{
}