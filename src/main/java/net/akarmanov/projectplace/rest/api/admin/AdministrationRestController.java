package net.akarmanov.projectplace.rest.api.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
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

@Tag(name = "Administration API")
@RequestMapping(
        value = "/api/v1/admin",
        produces = APPLICATION_JSON_VALUE)
public interface AdministrationRestController {

    @PostMapping("/confirm")
    @Operation(summary = "Подтверждение пользователя.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> confirm(@RequestParam UUID userId);

    @PostMapping("/unconfirm")
    @Operation(summary = "Отмена подтверждения пользователя.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> unconfirm(@RequestParam UUID userId);

    @GetMapping(value = "/users/all", produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Page<UserDTO>> all(@ParameterObject @PageableDefault Pageable pageable);

}
