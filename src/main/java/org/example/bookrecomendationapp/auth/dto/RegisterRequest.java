package org.example.bookrecomendationapp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.bookrecomendationapp.user.password.Password;

public record RegisterRequest(
        @NotBlank(message = "Name is mandatory")
        String name,
        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is mandatory")
        String email,
        @Password
        String password) {
}
