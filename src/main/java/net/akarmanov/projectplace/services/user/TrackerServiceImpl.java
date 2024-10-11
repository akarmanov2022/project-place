package net.akarmanov.projectplace.services.user;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.persistence.entities.Tracker;
import net.akarmanov.projectplace.persistence.entities.TrackerStatus;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.TrackerRepository;
import net.akarmanov.projectplace.services.exceptions.TrackerExistsException;
import net.akarmanov.projectplace.services.exceptions.TrackerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackerServiceImpl implements TrackerService {

    private final TrackerRepository trackerRepository;

    @Override
    public boolean existsByUserId(UUID userId) {
        return trackerRepository.existsByUserId(userId);
    }

    @Override
    public void createTracker(User user) {

        if (existsByUserId(user.getId())) {
            throw new TrackerExistsException(user.getId());
        }

        var tracker = Tracker.builder()
                .user(user)
                .createDate(LocalDateTime.now())
                .status(TrackerStatus.NOT_CONFIRMED)
                .build();
        saveTracker(tracker);
    }

    @Override
    public Tracker getTracker(UUID userId) {
        return trackerRepository.findByUserId(userId)
                .orElseThrow(() -> new TrackerNotFoundException(userId));
    }

    @Override
    public void saveTracker(Tracker tracker) {
        trackerRepository.save(tracker);
    }

    @Override
    public void confirmTracker(UUID userId) {
        changeTrackerStatus(userId, TrackerStatus.CONFIRMED);
    }

    @Override
    public void unconfirmTracker(UUID userId) {
        changeTrackerStatus(userId, TrackerStatus.NOT_CONFIRMED);

    }

    @Override
    public Page<Tracker> findAll(Pageable pageable) {
        return trackerRepository.findAll(pageable);
    }

    private void changeTrackerStatus(UUID userId, TrackerStatus trackerStatus) {
        if (!trackerRepository.existsByUserId(userId)) {
            throw new TrackerNotFoundException(userId);
        }
        trackerRepository.changeTrackerStatus(userId, trackerStatus);
    }
}
