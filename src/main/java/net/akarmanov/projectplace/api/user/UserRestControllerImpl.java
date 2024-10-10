package net.akarmanov.projectplace.api.user;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.services.user.UserPhotoService;
import net.akarmanov.projectplace.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<UserDTO> getUser(UUID id) {
        var user = userService.getUser(id);
        var userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        var users = userService.getUsers();
        List<UserDTO> userDTOs = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());
        return ResponseEntity.ok(userDTOs);
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserCreateDTO userCreateDTO) {
        var user = modelMapper.map(userCreateDTO, User.class);
        var createdUser = userService.createUser(user);
        var userDTO = modelMapper.map(createdUser, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UUID id, UserDTO request) {
        var user = modelMapper.map(request, User.class);
        var updatedUser = userService.updateUser(id, user);
        var userDTO = modelMapper.map(updatedUser, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> deleteUser(String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> addPhoto(UUID id, MultipartFile file) {
        Assert.notNull(file, "File must not be null");
        userService.addPhoto(id, file);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Resource> getPhoto(UUID id) {
        var userPhoto = userPhotoService.getPhoto(id);
        var resource = new ByteArrayResource(userPhoto.getPhoto());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + userPhoto.getFileName())
                .body(resource);
    }
}
