package com.atmosware.listentomusic.repository;

import com.atmosware.listentomusic.entities.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByUsernameInfo(String usernameInfo);
}
