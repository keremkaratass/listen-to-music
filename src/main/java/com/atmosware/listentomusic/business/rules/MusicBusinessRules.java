package com.atmosware.listentomusic.business.rules;

import com.atmosware.listentomusic.common.constants.Messages;
import com.atmosware.listentomusic.core.exceptions.BusinessException;
import com.atmosware.listentomusic.repository.MusicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MusicBusinessRules {
  private final MusicRepository repository;

  public void checkIfMusicExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Music.NotExists);
  }

  public void checkIfMusicExistsByName(String name) {
    if (repository.existsByNameIgnoreCase(name)) {
      throw new BusinessException(Messages.Music.Exists);
    }
  }
}
