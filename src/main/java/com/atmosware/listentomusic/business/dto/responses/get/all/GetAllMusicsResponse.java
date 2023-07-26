package com.atmosware.listentomusic.business.dto.responses.get.all;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMusicsResponse {
  private UUID id;
  private UUID albumId;
  private UUID genreId;
  private UUID artistId;
  private int favoriteNumber;
  private String name;
  private String albumName;
  private String genreName;
  private String artistName;
}
