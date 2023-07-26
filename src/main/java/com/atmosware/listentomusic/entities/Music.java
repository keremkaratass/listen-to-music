package com.atmosware.listentomusic.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "musics")
public class Music {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  private int favoriteNumber;

  @ManyToOne
  @JoinColumn(name = "album_id")
  private Album album;

  @ManyToOne()
  @JoinColumn(name = "genre_id")
  private Genre genre;

  @ManyToOne
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @ManyToMany(mappedBy = "musics")
  private List<User> users;
}
