package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.requests.create.CreateAlbumRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateAlbumRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetAlbumResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllAlbumsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateAlbumResponse;

import java.util.List;
import java.util.UUID;

public interface AlbumService {
    List<GetAllAlbumsResponse> getAll();

    GetAlbumResponse getById(UUID id);

    CreateAlbumResponse add(CreateAlbumRequest request);

    UpdateAlbumResponse update(UUID id, UpdateAlbumRequest request);
    void delete(UUID id);

}
