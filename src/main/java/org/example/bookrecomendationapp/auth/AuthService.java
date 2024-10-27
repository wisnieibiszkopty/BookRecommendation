package org.example.bookrecomendationapp.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookrecomendationapp.auth.dto.AuthRequest;
import org.example.bookrecomendationapp.auth.dto.AuthResponse;
import org.example.bookrecomendationapp.auth.dto.RegisterRequest;
import org.example.bookrecomendationapp.exceptions.UserAlreadyExistsException;
import org.example.bookrecomendationapp.user.User;
import org.example.bookrecomendationapp.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        Optional<User> existingUser = repository.findByEmail(request.email());
        if(existingUser.isPresent()){
            log.info("User already exists");
            throw new UserAlreadyExistsException();
        }

        User user = User
            .builder()
            .name(request.name())
            .email(request.email())
            .password(passwordEncoder.encode(request.password()))
            .build();

        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        User user = repository.findByEmail(request.email()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}

