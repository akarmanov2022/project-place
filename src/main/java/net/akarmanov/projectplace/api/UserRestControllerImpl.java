package net.akarmanov.projectplace.api;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestControllerImpl implements UserRestController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> getUser(UserDTO request) {
        final var user = userService.getUser(request);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO request) {
        final var user = userService.createUser(request);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO request) {
        final var user = userService.updateUser(request);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserDTO> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
