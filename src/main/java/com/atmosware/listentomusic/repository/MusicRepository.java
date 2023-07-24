package com.atmosware.listentomusic.repository;

import com.atmosware.listentomusic.entities.Music;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MusicRepository extends JpaRepository<Music, UUID> {
    @Query("SELECT m FROM Music m ORDER BY m.favoriteNumber DESC")
    List<Music> getMusicByFavoriteNumber();
}
