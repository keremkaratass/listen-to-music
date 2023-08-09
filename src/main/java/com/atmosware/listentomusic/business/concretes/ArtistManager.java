package com.atmosware.listentomusic.business.concretes;

import com.atmosware.listentomusic.business.abstracts.ArtistService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllArtistsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateArtistResponse;
import com.atmosware.listentomusic.business.rules.ArtistBusinessRules;
import com.atmosware.listentomusic.entities.Artist;
import com.atmosware.listentomusic.repository.ArtistRepository;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArtistManager implements ArtistService {

  private final ArtistRepository repository;
  private final ModelMapper mapper;
  private final ArtistBusinessRules rules;

  @Override
  public List<GetAllArtistsResponse> getAll() {
    var artists = repository.findAll();
    return artists.stream().map(artist -> mapper.map(artist, GetAllArtistsResponse.class)).toList();
  }

  @Override
  public GetArtistResponse getById(UUID id) {
    rules.checkIfArtistExists(id);
    var artist = repository.findById(id).orElseThrow();
    return mapper.map(artist, GetArtistResponse.class);
  }

  @Override
  public CreateArtistResponse add(CreateArtistRequest request) {
    rules.checkIfArtistExistsByName(request.getName());
    var artist = mapper.map(request, Artist.class);
    artist.setId(UUID.randomUUID());
    repository.save(artist);
    return mapper.map(artist, CreateArtistResponse.class);
  }

  @Override
  public UpdateArtistResponse update(UUID id, UpdateArtistRequest request) {
    var artist = mapper.map(request, Artist.class);
    artist.setId(id);
    repository.save(artist);
    return mapper.map(artist, UpdateArtistResponse.class);
  }

  @Override
  public void delete(UUID id) {
    rules.checkIfArtistExists(id);
    repository.deleteById(id);
  }
}
