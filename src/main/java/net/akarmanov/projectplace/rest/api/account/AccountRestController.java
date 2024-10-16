package net.akarmanov.projectplace.rest.api.account;

import jakarta.validation.Valid;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
import net.akarmanov.projectplace.rest.api.dto.UserUpdateDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users/")
public interface AccountRestController {

    @GetMapping("/current/info")
    ResponseEntity<UserDTO> getCurrentUserInfo();

    @PostMapping(
            value = "/current/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> updateCurrentUserInfo(@Valid @RequestBody UserUpdateDTO userDTO);

    @PostMapping("/current/changePassword")
    ResponseEntity<Void> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword);


}
