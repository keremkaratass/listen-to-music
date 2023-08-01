package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.ArtistService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllArtistsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateArtistResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/artists")
public class ArtistsController {
  private final ArtistService service;

  @GetMapping()
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllArtistsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public GetArtistResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateArtistResponse add(@Valid @RequestBody CreateArtistRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public UpdateArtistResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateArtistRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
