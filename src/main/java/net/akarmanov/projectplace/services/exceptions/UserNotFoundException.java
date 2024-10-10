package net.akarmanov.projectplace.services.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("User with id " + id + " not found");
    }

    public UserNotFoundException(String telegramId) {
        super("User with telegramId " + telegramId + " not found");
    }
}
