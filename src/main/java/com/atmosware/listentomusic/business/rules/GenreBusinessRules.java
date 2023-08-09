package com.atmosware.listentomusic.business.rules;

import com.atmosware.listentomusic.common.constants.Messages;
import com.atmosware.listentomusic.core.exceptions.BusinessException;
import com.atmosware.listentomusic.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreBusinessRules {
  private final GenreRepository repository;

  public void checkIfGenreExists(UUID id) {
    if (!repository.existsById(id)) throw new BusinessException(Messages.Genre.NotExists);
  }

  public void checkIfGenreExistsByName(String name) {
    if (repository.existsByNameIgnoreCase(name)) {
      throw new BusinessException(Messages.Genre.Exists);
    }
  }
}
