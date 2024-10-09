package net.akarmanov.projectplace.services;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.api.user.UserDTO;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public UserDTO getUser(String id) {
        var uuid = UUID.fromString(id);
        var user = userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(uuid));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        var user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UUID id, UserDTO userDTO) {
        var updateUser = modelMapper.map(userDTO, User.class);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        updateUser.setId(user.getId());
        var updatedUser = userRepository.save(updateUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

}
