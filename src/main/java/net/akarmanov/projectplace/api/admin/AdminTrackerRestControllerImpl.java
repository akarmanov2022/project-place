package net.akarmanov.projectplace.api.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.akarmanov.projectplace.api.dto.TrackerDTO;
import net.akarmanov.projectplace.services.admin.AdministrationService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminTrackerRestControllerImpl implements AdminTrackerRestController {

    private final ModelMapper modelMapper;

    private final AdministrationService administrationService;

    @Override
    public ResponseEntity<Void> confirm(UUID userId) {
        administrationService.confirmTracker(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> unconfirm(UUID userId) {
        administrationService.unconfirmTracker(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<TrackerDTO>> all(Pageable pageable) {
        var trackers = administrationService.getAllTrackers(pageable);
        var trackerDTOs = trackers.map(tracker -> modelMapper.map(tracker, TrackerDTO.class));
        return ResponseEntity.ok(trackerDTOs);
    }
}
