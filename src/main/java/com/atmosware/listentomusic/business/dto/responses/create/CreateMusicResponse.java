package com.atmosware.listentomusic.business.dto.responses.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMusicResponse {
  private UUID id;
  private UUID albumId;
  private UUID genreId;
  private int favoriteNumber;
  private String name;

}
