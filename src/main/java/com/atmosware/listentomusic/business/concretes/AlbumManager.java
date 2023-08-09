package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.AlbumService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllAlbumsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateAlbumResponse;
import com.atmosware.listentomusic.business.rules.AlbumBusinessRules;
import com.atmosware.listentomusic.entities.Album;
import com.atmosware.listentomusic.repository.AlbumRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumManager implements AlbumService {

  private final AlbumRepository repository;
  private final ModelMapper mapper;
  private final AlbumBusinessRules rules;

  @Override
  public List<GetAllAlbumsResponse> getAll() {
    var albums = repository.findAll();
    return albums.stream().map(album -> mapper.map(album, GetAllAlbumsResponse.class)).toList();
  }

  @Override
  public GetAlbumResponse getById(UUID id) {
    rules.checkIfAlbumExists(id);
    var album = repository.findById(id).orElseThrow();
    return mapper.map(album, GetAlbumResponse.class);
  }

  @Override
  public CreateAlbumResponse add(CreateAlbumRequest request) {
    rules.checkIfAlbumExistsByName(request.getName());
    var album = mapper.map(request, Album.class);
    album.setId(UUID.randomUUID());
    album.setReleaseDate(LocalDateTime.now());
    repository.save(album);
    return mapper.map(album, CreateAlbumResponse.class);
  }

  @Override
  public UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request) {
    var album = mapper.map(request, Album.class);
    album.setId(id);
    repository.save(album);
    return mapper.map(album, UpdateAlbumResponse.class);
  }

  @Override
  public void delete(UUID id) {
    rules.checkIfAlbumExists(id);
    repository.deleteById(id);
  }
}
