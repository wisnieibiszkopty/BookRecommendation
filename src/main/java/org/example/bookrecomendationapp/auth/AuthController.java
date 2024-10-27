package org.example.bookrecomendationapp.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bookrecomendationapp.auth.dto.AuthRequest;
import org.example.bookrecomendationapp.auth.dto.AuthResponse;
import org.example.bookrecomendationapp.auth.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
