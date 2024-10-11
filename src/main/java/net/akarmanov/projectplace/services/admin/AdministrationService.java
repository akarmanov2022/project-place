package net.akarmanov.projectplace.services.admin;

import net.akarmanov.projectplace.persistence.entities.Tracker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AdministrationService {
    void confirmTracker(UUID userId);

    void unconfirmTracker(UUID userId);

    Page<Tracker> getAllTrackers(Pageable pageable);
}
