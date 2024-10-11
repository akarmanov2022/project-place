package net.akarmanov.projectplace.api.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.akarmanov.projectplace.api.dto.TrackerDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Tracker API")
@RequestMapping(
        value = "/api/v1/admin/tracker",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
public interface AdminTrackerRestController {

    @PostMapping("/confirm")
    @Operation(summary = "Подтверждение трекера.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> confirm(@RequestParam UUID userId);

    @PostMapping("/unconfirm")
    @Operation(summary = "Отмена подтверждения трекера.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> unconfirm(@RequestParam UUID userId);

    @GetMapping("/all")
    ResponseEntity<Page<TrackerDTO>> all(@ParameterObject @PageableDefault Pageable pageable);

}
