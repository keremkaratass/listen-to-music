package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.responses.MusicDTO;
import java.util.List;
import java.util.UUID;

public interface FavoriteService {
  void favoriteMusic(UUID userId, UUID musicId);

  void unfavoriteMusic(UUID userId, UUID musicId);

  List<MusicDTO> getFavoriteMusics(UUID userId);
}
