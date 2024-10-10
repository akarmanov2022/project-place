package net.akarmanov.projectplace.persistence.jpa;

import net.akarmanov.projectplace.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByTelegramId(String telegramId);

    boolean existsUserByUsername(String username);

    boolean existsUserByTelegramId(String telegramId);

    Optional<User> findByUsername(String username);
}
