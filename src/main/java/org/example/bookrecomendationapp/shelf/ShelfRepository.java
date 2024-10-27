package org.example.bookrecomendationapp.shelf;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ShelfRepository extends JpaRepository<Shelf, Long> {

    List<Shelf> findShelfByUserId(Long id);

}
