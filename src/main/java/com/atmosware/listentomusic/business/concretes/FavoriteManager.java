package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.FavoriteService;
import com.atmosware.listentomusic.business.dto.responses.MusicDTO;
import com.atmosware.listentomusic.business.dto.responses.UserDTO;
import com.atmosware.listentomusic.entities.Music;
import com.atmosware.listentomusic.entities.User;
import com.atmosware.listentomusic.repository.MusicRepository;
import com.atmosware.listentomusic.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteManager implements FavoriteService {
  private final UserRepository userRepository;
  private final MusicRepository musicRepository;

  @Override
  public void favoriteMusic(UUID userId, UUID musicId) {
    var user = userRepository.findById(userId).orElseThrow();
    var music = musicRepository.findById(musicId).orElseThrow();

    if (!user.getMusics().contains(music)) {
      user.getMusics().add(music);
      music.getUsers().add(user);
      music.setFavoriteNumber(music.getFavoriteNumber() + 1);
      userRepository.save(user);
      musicRepository.save(music);
    }
  }

  @Override
  public void unfavoriteMusic(UUID userId, UUID musicId) {
    var user = userRepository.findById(userId).orElseThrow();
    var music = musicRepository.findById(musicId).orElseThrow();

    if (user.getMusics().contains(music)) {
      user.getMusics().remove(music);
      music.getUsers().remove(user);
      music.setFavoriteNumber(music.getFavoriteNumber() - 1);
      userRepository.save(user);
      musicRepository.save(music);
    }
  }

  @Override
  public List<MusicDTO> getFavoriteMusics(UUID userId) {
    var user = userRepository.findById(userId).orElseThrow();
    List<Music> favoriteMusics = user.getMusics();
    return favoriteMusics.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public List<MusicDTO> getFollowedFavoriteMusics(UUID userId) {
    var user = userRepository.findById(userId).orElseThrow();

    List<User> followedUsers = user.getFollowedUsers();
    List<Music> favoriteMusics = new ArrayList<>();

    followedUsers.forEach(followedUser -> favoriteMusics.addAll(followedUser.getMusics()));

    return favoriteMusics.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  @Override
  public void followUser(UUID followerId, UUID followedId) {
    User follower = userRepository.findById(followerId).orElseThrow();
    User followed = userRepository.findById(followedId).orElseThrow();

    follower.getFollowedUsers().add(followed);
    userRepository.save(follower);
  }

  @Override
  public void unfollowUser(UUID followerId, UUID followedId) {
    User follower = userRepository.findById(followerId).orElseThrow();
    User followed = userRepository.findById(followedId).orElseThrow();

    follower.getFollowedUsers().remove(followed);
    userRepository.save(follower);
  }

  @Override
  public List<UserDTO> getFollowedUsers(UUID userId) {
    User user = userRepository.findById(userId).orElseThrow();
    List<User> followedUsers = user.getFollowedUsers();

    List<UserDTO> followedUserDTOs = followedUsers.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());

    return followedUserDTOs;
  }

  private MusicDTO convertToDTO(Music music) {
    return new MusicDTO(music.getId(), music.getName(), music.getFavoriteNumber());
  }

  private UserDTO convertToDTO(User user) {

    return new UserDTO(user.getId(), user.getFirstName(), user.getLastName());
  }
}
