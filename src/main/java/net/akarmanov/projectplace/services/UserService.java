package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO getUser(UUID id);

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    void deleteUser(String id);

    void addPhoto(UUID userId, MultipartFile file);

    List<UserDTO> getUsers();
}
