package net.akarmanov.projectplace.persistence.jpa;

import net.akarmanov.projectplace.persistence.entities.Tracker;
import net.akarmanov.projectplace.persistence.entities.TrackerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TrackerRepository extends JpaRepository<Tracker, UUID>, JpaSpecificationExecutor<Tracker> {
    boolean existsByUserId(UUID userId);

    Optional<Tracker> findByUserId(UUID userId);

    @Modifying(flushAutomatically = true)
    @Query("update Tracker t set t.status = :status where t.user.id = :userId")
    void changeTrackerStatus(UUID userId, TrackerStatus status);
}
