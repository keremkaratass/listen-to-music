package com.atmosware.listentomusic.business.abstracts;

import com.atmosware.listentomusic.business.dto.requests.create.CreateMusicRequest;
import com.atmosware.listentomusic.business.dto.requests.update.UpdateMusicRequest;
import com.atmosware.listentomusic.business.dto.responses.create.CreateMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.GetMusicResponse;
import com.atmosware.listentomusic.business.dto.responses.get.all.GetAllMusicsResponse;
import com.atmosware.listentomusic.business.dto.responses.update.UpdateMusicResponse;
import java.util.List;
import java.util.UUID;

public interface MusicService {
    List<GetAllMusicsResponse> getAll();

    GetMusicResponse getById(UUID id);

    CreateMusicResponse add(CreateMusicRequest request);

    UpdateMusicResponse update(UUID id, UpdateMusicRequest request);
    void delete(UUID id);

    List<GetAllMusicsResponse> getAllByFavoriteNumber(int page, int size);





}
