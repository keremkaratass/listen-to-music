package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.security.AuthenticationRequestDto;
import com.atmosware.listentomusic.security.AuthenticationResponseDto;
import com.atmosware.listentomusic.security.AuthenticationService;
import com.atmosware.listentomusic.security.RegisterRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDto> register(
      @RequestBody RegisterRequestDto request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @RequestBody AuthenticationRequestDto request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    service.refreshToken(request, response);
  }
}
