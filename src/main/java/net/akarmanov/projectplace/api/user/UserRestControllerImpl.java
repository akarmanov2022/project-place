package net.akarmanov.projectplace.api.user;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.services.UserPhotoService;
import net.akarmanov.projectplace.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserRestControllerImpl implements UserRestController {

    private final UserService userService;

    private final UserPhotoService userPhotoService;

    @Override
    public ResponseEntity<UserDTO> getUser(String id) {
        final var user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserCreateDTO userCreateDTO) {
        final var user = userService.createUser(userCreateDTO);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UUID id, UserDTO userDTO) {
        final var user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addPhoto(UUID userId, MultipartFile file) {
        userService.addPhoto(userId, file);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<byte[]> getPhoto(UUID userId) {
        var userPhoto = userPhotoService.getPhoto(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + userPhoto.getFileName())
                .body(userPhoto.getPhoto());
    }
}
