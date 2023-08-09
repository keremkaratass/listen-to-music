package com.atmosware.listentomusic.api.controllers;

import com.atmosware.listentomusic.business.abstracts.FavoriteService;
import com.atmosware.listentomusic.business.dto.responses.UserDTO;
import com.atmosware.listentomusic.entities.User;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
  private final FavoriteService favoriteService;

  @PostMapping("/follow")
  public void followUser(
      @RequestParam UUID followerId, @RequestParam UUID followedId, Authentication authentication) {

    User loggedInUser = (User) authentication.getPrincipal();
    UUID loggedInUserId = loggedInUser.getId();

    if (!loggedInUserId.equals(followerId)) {
      throw new RuntimeException("You don't have permission to follow.");
    }

    favoriteService.followUser(followerId, followedId);
  }

  @PostMapping("/unfollow")
  public void unfollowUser(
      @RequestParam UUID followerId, @RequestParam UUID followedId, Authentication authentication) {

    User loggedInUser = (User) authentication.getPrincipal();
    UUID loggedInUserId = loggedInUser.getId();

    if (!loggedInUserId.equals(followerId)) {
      throw new RuntimeException("You don't have permission to unfollow.");
    }

    favoriteService.unfollowUser(followerId, followedId);
  }

  @GetMapping("/followed-users")
  public List<UserDTO> getFollowedUsers(Authentication authentication) {
    User loggedInUser = (User) authentication.getPrincipal();
    UUID loggedInUserId = loggedInUser.getId();
    return favoriteService.getFollowedUsers(loggedInUserId);
  }
}
