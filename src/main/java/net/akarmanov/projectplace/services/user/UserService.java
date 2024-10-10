package net.akarmanov.projectplace.services.user;

import net.akarmanov.projectplace.persistence.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUser(UUID id);

    User getUserByTelegramId(String telegramId);

    User getUserByUsername(String username);

    User createUser(User userCreateDTO);

    User updateUser(UUID id, User userDTO);

    void deleteUser(String id);

    List<User> getUsers();

    User getCurrentUser();

    UserDetailsService getDetailsService();

    boolean existsByUsername(String username);
}
