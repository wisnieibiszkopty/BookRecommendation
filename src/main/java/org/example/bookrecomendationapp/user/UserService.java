package org.example.bookrecomendationapp.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getCurrentUser(String name){
        return userRepository.findByName(name)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't exists"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
