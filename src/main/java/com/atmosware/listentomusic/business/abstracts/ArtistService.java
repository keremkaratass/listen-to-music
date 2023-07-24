package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.requests.create.CreateArtistRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateArtistRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetArtistResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllArtistsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateArtistResponse;
import java.util.List;
import java.util.UUID;

public interface ArtistService {
    List<GetAllArtistsResponse> getAll();

    GetArtistResponse getById(UUID id);

    CreateArtistResponse add(CreateArtistRequest request);

    UpdateArtistResponse update(UUID id, UpdateArtistRequest request);
    void delete(UUID id);
}
