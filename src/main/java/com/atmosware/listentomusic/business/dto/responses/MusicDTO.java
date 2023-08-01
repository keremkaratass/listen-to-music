package com.atmosware.listentomusic.business.dto.responses;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicDTO {
    private UUID id;
    private String name;
    private int favoriteNumber;
    // Other fields as needed
}