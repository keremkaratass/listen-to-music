package com.atmosware.listentomusic.business.dto.responses.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAlbumResponse {
    private UUID id;
    private UUID artistId;
    private String name;
    private LocalDateTime releaseDate;
}
