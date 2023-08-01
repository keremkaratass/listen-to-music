package com.atmosware.listentomusic.security;

import com.atmosware.listentomusic.entities.enums.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;
}