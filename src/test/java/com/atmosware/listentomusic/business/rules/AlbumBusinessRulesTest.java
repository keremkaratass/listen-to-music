package com.atmosware.listentomusic.business.rules;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.atmosware.listentomusic.core.exceptions.BusinessException;
import com.atmosware.listentomusic.repository.AlbumRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AlbumBusinessRulesTest {
  @Autowired 
  private AlbumBusinessRules albumBusinessRules;
  @MockBean
  private AlbumRepository repository;
  @Test
  void testCheckIfAlbumExists() {
    assertThrows(
        BusinessException.class, () -> albumBusinessRules.checkIfAlbumExists(UUID.randomUUID()));
  }

  @Test
  void testCheckIfAlbumExists2() {

    albumBusinessRules.checkIfAlbumExists(UUID.fromString("1f0e7e76-2eef-43e7-b5c0-2b31613fc7f9"));
  }
}
