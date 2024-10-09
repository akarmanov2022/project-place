package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.persistence.entities.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {
    UserDTO getUser(String id);

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    void deleteUser(String id);

    void addPhoto(UUID userId, MultipartFile file);

    UserPhoto getPhoto(UUID id);

    UserDTO getUser(UUID id);
}
