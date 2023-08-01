package com.atmosware.listentomusic.security;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {
  private String username;
  private String password;
}



