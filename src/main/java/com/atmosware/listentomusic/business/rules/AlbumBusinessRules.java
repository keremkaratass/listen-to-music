package com.atmosware.listentomusic.business.rules;

import com.atmosware.listentomusic.common.constants.Messages;
import com.atmosware.listentomusic.core.exceptions.BusinessException;
import com.atmosware.listentomusic.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AlbumBusinessRules {
  private final AlbumRepository repository;

  public void checkIfAlbumExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Album.NotExists);
  }

  public void checkIfAlbumExistsByName(String name) {
    if (repository.existsByNameIgnoreCase(name)) {
      throw new BusinessException(Messages.Album.Exists);
    }
  }
}
