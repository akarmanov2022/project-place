package net.akarmanov.projectplace.services.admin;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.domain.User;
import net.akarmanov.projectplace.services.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class AdministrationServiceImpl implements AdministrationService {

    private final UserService userService;

    @Override
    public void confirmUser(UUID userId) {
        userService.enableUser(userId);
    }

    @Override
    public void unconfirmUser(UUID userId) {
        userService.disableUser(userId);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
