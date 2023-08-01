package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.FavoriteService;
import com.atmosware.listentomusic.business.abstracts.MusicService;
import com.atmosware.listentomusic.business.dto.requests.create.CreateMusicRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateMusicRequest;
import com.atmosware.listentomusic.business.dto.responses.MusicDTO;
import com.atmosware.listentomusic.business.dto.responses.create.CreateMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllMusicsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateMusicResponse;
import com.atmosware.listentomusic.entities.User;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/musics")
public class MusicsController {
  private final MusicService service;
  private final FavoriteService favoriteService;

  @GetMapping()
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public GetMusicResponse getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public CreateMusicResponse add(@Valid @RequestBody CreateMusicRequest request) {
    return service.add(request);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public UpdateMusicResponse update(
      @PathVariable UUID id, @Valid @RequestBody UpdateMusicRequest request) {
    return service.update(id, request);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }

  @GetMapping("/popular")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> getAllByFavoriteNumber(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
    return service.getAllByFavoriteNumber(page, size);
  }

  @GetMapping("/searchByArtist")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> searchMusicByArtist(@RequestParam String artist) {
    return service.searchMusicByArtist(artist);
  }

  @GetMapping("/searchByAlbum")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> searchMusicByAlbum(@RequestParam String album) {
    return service.searchMusicByAlbum(album);
  }

  @GetMapping("/searchByGenre")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> searchMusicByGenre(@RequestParam String genre) {
    return service.searchMusicByGenre(genre);
  }

  @GetMapping("/searchByArtistAndGenre")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<GetAllMusicsResponse> searchMusicByArtistAndGenre(
      @RequestParam String artist, @RequestParam String genre) {
    return service.searchMusicByArtistAndGenre(artist, genre);
  }

  @PostMapping("/{musicId}/favorite")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public void favoriteMusic(@PathVariable UUID musicId, Authentication authentication) {
    UUID userId = ((User) authentication.getPrincipal()).getId();
    favoriteService.favoriteMusic(userId, musicId);
  }

  @PostMapping("/{musicId}/unfavorite")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public void unfavoriteMusic(@PathVariable UUID musicId, Authentication authentication) {
    UUID userId = ((User) authentication.getPrincipal()).getId();
    favoriteService.unfavoriteMusic(userId, musicId);
  }

  @GetMapping("/favorites")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public List<MusicDTO> getFavoriteMusics(Authentication authentication) {
    UUID userId = ((User) authentication.getPrincipal()).getId();
    return favoriteService.getFavoriteMusics(userId);
  }
  
  
}
