package org.example.bookrecomendationapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Opinion already exists")
public class OpinionAlreadyExistsException extends RuntimeException{
}
