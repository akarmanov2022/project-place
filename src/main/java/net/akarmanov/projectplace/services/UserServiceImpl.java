package net.akarmanov.projectplace.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.akarmanov.projectplace.api.user.dto.UserCreateDTO;
import net.akarmanov.projectplace.api.user.dto.UserDTO;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final UserPhotoService userPhotoService;

    @Override
    public UserDTO getUser(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        var user = modelMapper.map(userCreateDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
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

    @SneakyThrows
    @Override
    @Transactional
    public void addPhoto(UUID userId, MultipartFile file) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userPhotoService.addPhoto(user, file);
    }

    @Override
    public List<UserDTO> getUsers() {
        var users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());
    }
}
