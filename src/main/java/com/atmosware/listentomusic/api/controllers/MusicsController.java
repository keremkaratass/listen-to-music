package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.MusicService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateMusicRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateMusicRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllMusicsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateMusicResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/musics")
public class MusicsController {
  private final MusicService service;

  @GetMapping()
  public List<GetAllMusicsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public GetMusicResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateMusicResponse add(@Valid @RequestBody CreateMusicRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateMusicResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateMusicRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }

  @GetMapping("/popular")
  public List<GetAllMusicsResponse> getAllByFavoriteNumber(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    return service.getAllByFavoriteNumber(page, size);
  }
}
