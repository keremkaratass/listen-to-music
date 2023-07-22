package com.atmosware.listentomusic.business.dto.responses.get.all;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllArtistsResponse {
    private UUID id;
    private String name;
}
