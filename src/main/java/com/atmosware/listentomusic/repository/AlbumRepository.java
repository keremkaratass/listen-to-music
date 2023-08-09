package com.atmosware.listentomusic.repository;

import com.atmosware.listentomusic.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    boolean existsByNameIgnoreCase(String name);
}