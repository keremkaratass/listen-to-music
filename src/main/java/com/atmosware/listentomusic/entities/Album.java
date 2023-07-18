package com.atmosware.listentomusic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "albums")
public class Album {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private LocalDateTime releaseDate;

  @ManyToOne
  @JoinColumn(name = "artist_id")
  private Artist artist;

  @OneToMany(mappedBy = "album")
  private List<Music> musics;
}
