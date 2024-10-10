package net.akarmanov.projectplace.api.user;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.services.UserPhotoService;
import net.akarmanov.projectplace.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserRestControllerImpl implements UserRestController {

    private final UserService userService;

    private final UserPhotoService userPhotoService;

    @Override
    public ResponseEntity<UserDTO> getUser(UUID id) {
        final var user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
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
    public ResponseEntity<Resource> getPhoto(UUID userId) {
        var userPhoto = userPhotoService.getPhoto(userId);
        var content = userPhoto.getPhoto();
        Assert.notNull(content, "Photo content is null");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + userPhoto.getFileName())
                .body(new ByteArrayResource(content));
    }
}
