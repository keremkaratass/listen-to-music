package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.GenreService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateGenreRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateGenreRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllGenresResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateGenreResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genres")
public class GenresController {
  private final GenreService service;

  @GetMapping
  public List<GetAllGenresResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("{id}")
  public GetGenreResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CreateGenreResponse add(@Valid @RequestBody CreateGenreRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  public UpdateGenreResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateGenreRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }
}
