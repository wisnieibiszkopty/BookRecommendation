package org.example.bookrecomendationapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Message not found")
public class OpinionNotFoundException extends RuntimeException{
}
