package com.atmosware.listentomusic.entities;

import com.atmosware.listentomusic.entities.enums.UserRole;
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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  private String name ;
  
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @ManyToMany
  @JoinTable(
      name = "user_music_favorite",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "music_id"))
  private List<Music> musics;

  
  
}
