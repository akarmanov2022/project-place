package net.akarmanov.projectplace.services;

import net.akarmanov.projectplace.api.UserDTO;

public interface UserService {
    UserDTO getUser(UserDTO request);

    UserDTO createUser(UserDTO request);

    UserDTO updateUser(UserDTO request);

    void deleteUser(String id);

    UserDTO getUserById(String id);
}
