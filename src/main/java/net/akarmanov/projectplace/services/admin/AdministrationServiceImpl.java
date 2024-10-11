package net.akarmanov.projectplace.services.admin;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.persistence.entities.Tracker;
import net.akarmanov.projectplace.services.user.TrackerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class AdministrationServiceImpl implements AdministrationService {

    private final TrackerService trackerService;

    @Override
    public void confirmTracker(UUID userId) {
        trackerService.confirmTracker(userId);
    }

    @Override
    public void unconfirmTracker(UUID userId) {
        trackerService.unconfirmTracker(userId);
    }

    @Override
    public Page<Tracker> getAllTrackers(Pageable pageable) {
        return trackerService.findAll(pageable);
    }
}
