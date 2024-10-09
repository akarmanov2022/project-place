package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.api.user.UserDTO;

import java.util.UUID;

public interface UserService {
    UserDTO getUser(String id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UUID id, UserDTO userDTO);

    void deleteUser(String id);

}
