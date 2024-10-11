package net.akarmanov.projectplace.services.user;

import net.akarmanov.projectplace.persistence.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {
    User getUser(UUID id);

    User getUserByTelegramId(String telegramId);

    User createUser(User userCreateDTO);

    User updateUser(UUID id, User userDTO);

    void deleteUser(String id);

    User getCurrentUser();

    UserDetailsService getDetailsService();

    boolean existsByUsername(String username);

    Page<User> findAll(Pageable pageable);

    void enableUser(UUID userId);

    void disableUser(UUID userId);
}
