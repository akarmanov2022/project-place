package net.akarmanov.projectplace.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping(value = "/api/v1/users", consumes = "application/json", produces = "application/json")
public interface UserRestController {
    @RequestMapping("/get")
    ResponseEntity<UserDTO> getUser(@RequestParam String id);

    @RequestMapping("/create")
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO request);

    @RequestMapping("/update")
    ResponseEntity<UserDTO> updateUser(@RequestParam UUID id,
                                       @RequestBody UserDTO request);

    @RequestMapping("/delete")
    ResponseEntity<UserDTO> deleteUser(@RequestParam String id);
}
