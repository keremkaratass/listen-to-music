package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.AlbumService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllAlbumsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateAlbumResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/albums")
public class AlbumsController {
  private final AlbumService service;

  @GetMapping
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllAlbumsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public GetAlbumResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateAlbumResponse add(@Valid @RequestBody CreateAlbumRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public UpdateAlbumResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateAlbumRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
