package com.atmosware.listentomusic.business.rules;

import com.atmosware.listentomusic.common.constants.Messages;
import com.atmosware.listentomusic.core.exceptions.BusinessException;
import com.atmosware.listentomusic.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistBusinessRules {
  private final ArtistRepository repository;

  public void checkIfArtistExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Artist.NotExists);
  }

  public void checkIfArtistExistsByName(String name) {
    if (repository.existsByNameIgnoreCase(name)) {
      throw new BusinessException(Messages.Artist.Exists);
    }
  }
}
