package net.akarmanov.projectplace.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Tag(name = "User Photo API", description = "Операции с фото пользователя")
@RequestMapping("/api/v1/users/{userId}/photo")
@PreAuthorize("hasRole('ROLE_TRACKER')")
public interface UserPhotoRestController {

    @Operation(summary = "Добавить фото пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Идентификатор пользователя", required = true)
            })
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> addPhoto(@PathVariable UUID userId, @RequestParam MultipartFile file);

    @Operation(
            summary = "Получить фото пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Идентификатор пользователя", required = true)
            })
    @GetMapping(produces = APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getPhoto(@PathVariable UUID userId);

    @Operation(
            summary = "Удалить фото пользователя",
            parameters = {
                    @Parameter(name = "userId", description = "Идентификатор пользователя", required = true)
            })
    @DeleteMapping(produces = APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Void> deletePhoto(@PathVariable UUID userId);
}
