package net.akarmanov.projectplace.services;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.UserDTO;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public UserDTO getUser(UserDTO request) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO request) {
        var user = modelMapper.map(request, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO request) {
        return null;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public UserDTO getUserById(String id) {
        var user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }
}
