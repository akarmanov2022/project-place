package net.akarmanov.projectplace.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User API", description = "Операции с пользователями")
@RequestMapping(
        value = "/api/v1/users",
        produces = APPLICATION_JSON_VALUE)
public interface UserRestController {

    @Operation(summary = "Получить информацию о пользователе")
    @GetMapping("/get")
    ResponseEntity<UserDTO> getUser(@RequestParam UUID id);

    @GetMapping("/")
    ResponseEntity<List<UserDTO>> getAllUsers();

    @Operation(summary = "Создать нового пользователя")
    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO);

    @Operation(summary = "Обновить информацию о пользователе")
    @PostMapping(value = "/update", consumes = APPLICATION_JSON_VALUE)
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
    @GetMapping(value = "/get-photo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<Resource> getPhoto(@RequestParam UUID id);

}
