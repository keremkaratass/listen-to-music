package com.atmosware.listentomusic.business.dto.responses.get.all;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllMusicsResponse {
  private UUID id;
  private UUID albumId;
  private UUID genreId;
  private int favoriteNumber;
  private String name;
  private String albumName;
  private String genreName;
}