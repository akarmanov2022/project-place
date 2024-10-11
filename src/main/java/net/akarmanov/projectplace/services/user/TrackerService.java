package net.akarmanov.projectplace.services.user;

import net.akarmanov.projectplace.persistence.entities.Tracker;
import net.akarmanov.projectplace.persistence.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TrackerService {
    boolean existsByUserId(UUID userId);

    void createTracker(User user);

    Tracker getTracker(UUID userId);

    void saveTracker(Tracker tracker);

    void confirmTracker(UUID userId);

    void unconfirmTracker(UUID userId);

    Page<Tracker> findAll(Pageable pageable);
}
