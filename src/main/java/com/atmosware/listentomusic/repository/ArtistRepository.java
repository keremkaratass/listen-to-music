package com.atmosware.listentomusic.repository;

import com.atmosware.listentomusic.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    boolean existsByNameIgnoreCase(String name);

}
