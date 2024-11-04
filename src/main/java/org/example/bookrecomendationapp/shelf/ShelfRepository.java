package org.example.bookrecomendationapp.shelf;

import org.example.bookrecomendationapp.shelf.dto.ShelfProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ShelfRepository extends JpaRepository<Shelf, Long> {


    @Query("select s from Shelf s where s.user.id = :id")
    List<ShelfProjection> findShelfByUserId(Long id);


}
