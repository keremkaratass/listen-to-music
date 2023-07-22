package com.atmosware.listentomusic.business.dto.requests.create;

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
public class CreateMusicRequest {
  @NotNull private UUID albumId;
  @NotNull private UUID genreId;
  @NotBlank private String name;
}
