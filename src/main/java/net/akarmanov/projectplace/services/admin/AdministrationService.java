package net.akarmanov.projectplace.services.admin;

import net.akarmanov.projectplace.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdministrationService {
    void confirmUser(UUID userId);

    void unconfirmUser(UUID userId);

    Page<User> getAllUsers(Pageable pageable);
}
