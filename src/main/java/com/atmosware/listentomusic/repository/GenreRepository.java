package com.atmosware.listentomusic.repository;

import com.atmosware.listentomusic.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {

    boolean existsByNameIgnoreCase(String name);
}
