package net.akarmanov.projectplace.services.admin;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.mapping.UserMapper;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
import net.akarmanov.projectplace.services.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class AdministrationServiceImpl implements AdministrationService {

    public static final UserMapper USER_MAPPER = UserMapper.INSTANCE;

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
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        var users = userService.findAll(pageable);
        return users.map(USER_MAPPER::toDto);
    }
}
