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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/albums")
public class AlbumsController {
  private final AlbumService service;

  @GetMapping
  public List<GetAllAlbumsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public GetAlbumResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateAlbumResponse add(@Valid @RequestBody CreateAlbumRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateAlbumResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateAlbumRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
