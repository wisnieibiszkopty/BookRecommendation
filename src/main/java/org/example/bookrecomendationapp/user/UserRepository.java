package org.example.bookrecomendationapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        SELECT u.id as id, u.name as name, u.email as email FROM User u WHERE u.id = :userId
    """)
    UserProjection findCurrentUser(@Param("userId") Long id);

    Optional<User> findByEmail(String email);

}
