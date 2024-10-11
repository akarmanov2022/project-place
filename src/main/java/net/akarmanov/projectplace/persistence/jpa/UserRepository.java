package net.akarmanov.projectplace.persistence.jpa;

import net.akarmanov.projectplace.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<User> findByTelegramId(String telegramId);

    boolean existsUserByTelegramId(String telegramId);
}
