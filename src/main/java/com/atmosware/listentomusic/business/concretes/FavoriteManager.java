package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.FavoriteService;
import com.atmosware.listentomusic.business.dto.responses.MusicDTO;
import com.atmosware.listentomusic.entities.Music;
import com.atmosware.listentomusic.repository.MusicRepository;
import com.atmosware.listentomusic.repository.UserRepository;
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
    return favoriteMusics.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  private MusicDTO convertToDTO(Music music) {
    return new MusicDTO(music.getId(), music.getName(), music.getFavoriteNumber());
  }
}
