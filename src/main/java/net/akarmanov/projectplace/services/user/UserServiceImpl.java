package net.akarmanov.projectplace.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.persistence.entities.User;
import net.akarmanov.projectplace.persistence.jpa.UserRepository;
import net.akarmanov.projectplace.services.exceptions.TelegramIdExistsException;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new UserNotFoundException(telegramId));
    }

    @Override
    public User createUser(User userCreate) {
        if (userRepository.existsUserByTelegramId(userCreate.getTelegramId())) {
            throw new TelegramIdExistsException(userCreate.getTelegramId());
        }

        return userRepository.save(userCreate);
    }

    @Override
    @Transactional
    public User updateUser(UUID id, User userDTO) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setTelegramId(userDTO.getTelegramId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setMiddleName(userDTO.getMiddleName());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByTelegramId(username);
    }

    @Override
    public UserDetailsService getDetailsService() {
        return this::getUserByTelegramId;
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsUserByTelegramId(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void enableUser(UUID userId) {
        changeUserState(userId, true);
    }

    @Override
    public void disableUser(UUID userId) {
        changeUserState(userId, false);
    }

    private void changeUserState(UUID userId, boolean enabled) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.setEnabled(enabled);
        userRepository.save(user);
    }
}
