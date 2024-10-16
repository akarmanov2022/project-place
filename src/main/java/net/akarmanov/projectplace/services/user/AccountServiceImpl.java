package net.akarmanov.projectplace.services.user;

import lombok.RequiredArgsConstructor;
import net.akarmanov.projectplace.mapping.UserMapper;
import net.akarmanov.projectplace.rest.api.dto.UserDTO;
import net.akarmanov.projectplace.rest.api.dto.UserUpdateDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    public static final UserMapper USER_MAPPER = UserMapper.INSTANCE;

    private final UserService userService;

    @Override
    public UserDTO getCurrentUserInfo() {
        var user = userService.getCurrentUser();
        return USER_MAPPER.toDto(user);
    }

    @Override
    public UserDTO updateUserInfo(UserUpdateDTO userDTO) {
        var user = userService.getCurrentUser();
        if (userDTO.phoneNumber() != null) {
            user.setPhoneNumber(userDTO.phoneNumber());
        }
        if (userDTO.firstName() != null) {
            user.setFirstName(userDTO.firstName());
        }
        if (userDTO.middleName() != null) {
            user.setMiddleName(userDTO.middleName());
        }
        if (userDTO.lastName() != null) {
            user.setLastName(userDTO.lastName());
        }
        if (userDTO.telegramId() != null) {
            user.setTelegramId(userDTO.telegramId());
        }

        var saved = userService.updateUser(user.getId(), user);
        return USER_MAPPER.toDto(saved);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        userService.changePassword(oldPassword, newPassword);
    }
}
