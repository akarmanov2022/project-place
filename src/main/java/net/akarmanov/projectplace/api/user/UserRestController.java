package net.akarmanov.projectplace.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Tag(name = "User API", description = "Операции с пользователями")
@RequestMapping(value = "/api/v1/users", consumes = "application/json", produces = "application/json")
public interface UserRestController {

    @Operation(summary = "Получить информацию о пользователе")
    @GetMapping("/get")
    ResponseEntity<UserDTO> getUser(@RequestParam String id);

    @Operation(summary = "Создать нового пользователя")
    @PostMapping("/create")
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO);

    @Operation(summary = "Обновить информацию о пользователе")
    @PostMapping("/update")
    ResponseEntity<UserDTO> updateUser(@RequestParam UUID id,
                                       @RequestBody @Valid UserDTO request);

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/delete")
    ResponseEntity<UserDTO> deleteUser(@RequestParam String id);

    @Operation(summary = "Добавить фото пользователя")
    @PostMapping(value = "/add-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> addPhoto(@RequestParam UUID id,
                                  @RequestParam MultipartFile file);

    @Operation(summary = "Получить фото пользователя")
    @GetMapping(value = "/get-photo", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<byte[]> getPhoto(@RequestParam UUID id);

}
