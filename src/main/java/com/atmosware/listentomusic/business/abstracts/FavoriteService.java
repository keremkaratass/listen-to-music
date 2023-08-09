package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.responses.MusicDTO;
import com.atmosware.listentomusic.business.dto.responses.UserDTO;
import java.util.List;
import java.util.UUID;

public interface FavoriteService {
  void favoriteMusic(UUID userId, UUID musicId);

  void unfavoriteMusic(UUID userId, UUID musicId);

  List<MusicDTO> getFavoriteMusics(UUID userId);

  List<MusicDTO> getFollowedFavoriteMusics(UUID userId);

  void followUser(UUID followerId, UUID followedId);

  void unfollowUser(UUID followerId, UUID followedId);

  List<UserDTO> getFollowedUsers(UUID userId);
}
