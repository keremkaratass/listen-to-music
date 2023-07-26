package com.atmosware.listentomusic.business.dto.requests.update;

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
public class UpdateMusicRequest {
  @NotNull private UUID albumId;
  @NotNull private UUID genreId;
  @NotNull private UUID artistId;
  @NotBlank private String name;
}
