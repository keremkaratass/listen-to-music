package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.MusicService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateMusicRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateMusicRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllMusicsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateMusicResponse;
import com.atmosware.listentomusic.entities.Music;
import com.atmosware.listentomusic.repository.MusicRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
@AllArgsConstructor
public class MusicManager implements MusicService {
  private MusicRepository repository;
  private ModelMapper mapper;

  @Override
  public List<GetAllMusicsResponse> getAll() {
    var musics = repository.findAll();
    return musics.stream().map(music -> mapper.map(music, GetAllMusicsResponse.class)).toList();
  }

  @Override
  public GetMusicResponse getById(UUID id) {
    var music = repository.findById(id).orElseThrow();
    return mapper.map(music, GetMusicResponse.class);
  }

  @Override
  public CreateMusicResponse add(CreateMusicRequest request) {
    var music = mapper.map(request, Music.class);
    music.setId(UUID.randomUUID());
    repository.save(music);
    return mapper.map(music, CreateMusicResponse.class);
  }

  @Override
  public UpdateMusicResponse update(UUID id, UpdateMusicRequest request) {
    var music = mapper.map(request, Music.class);
    music.setId(id);
    repository.save(music);
    return mapper.map(music, UpdateMusicResponse.class);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }

  @Override
  @Cacheable(value = "popularMusics", key = "'page:' + #page + ':size:' + #size")
  public List<GetAllMusicsResponse> getAllByFavoriteNumber(int page, int size) {
    var musics = repository.getMusicByFavoriteNumber();
    int fromIndex = (page - 1) * size;
    int toIndex = Math.min(fromIndex + size, musics.size());

    return musics.subList(fromIndex, toIndex).stream()
        .map(music -> mapper.map(music, GetAllMusicsResponse.class))
        .toList();
  }

  @Override
  public List<GetAllMusicsResponse> searchMusicByArtist(String artist) {
    List<Music> musics = repository.findByArtistNameIgnoreCaseContaining(artist);
    return musics.stream().map(music -> mapper.map(music, GetAllMusicsResponse.class)).toList();
  }

  @Override
  public List<GetAllMusicsResponse> searchMusicByAlbum(String album) {
    List<Music> musics = repository.findByAlbumNameIgnoreCaseContaining(album);
    return musics.stream().map(music -> mapper.map(music, GetAllMusicsResponse.class)).toList();
  }

  @Override
  public List<GetAllMusicsResponse> searchMusicByGenre(String genre) {
    List<Music> musics = repository.findByGenreNameIgnoreCaseContaining(genre);
    return musics.stream().map(music -> mapper.map(music, GetAllMusicsResponse.class)).toList();
  }

  public List<GetAllMusicsResponse> searchMusicByArtistAndGenre(String artist, String genre) {
    List<Music> musics =
        repository.findByArtistNameIgnoreCaseContainingAndGenreNameIgnoreCaseContaining(
            artist, genre);
    return musics.stream().map(music -> mapper.map(music, GetAllMusicsResponse.class)).toList();
  }
}
