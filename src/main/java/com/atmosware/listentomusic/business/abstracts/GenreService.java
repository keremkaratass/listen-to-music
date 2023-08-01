package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.requests.create.CreateGenreRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateGenreRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetGenreResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllGenresResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateGenreResponse;
import java.util.List;
import java.util.UUID;

public interface GenreService {
    List<GetAllGenresResponse> getAll();

    GetGenreResponse getById(UUID id);

    CreateGenreResponse add(CreateGenreRequest request);

    UpdateGenreResponse update(UUID id, UpdateGenreRequest request);
    void delete(UUID id);

}
