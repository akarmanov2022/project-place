package net.akarmanov.projectplace.services.exceptions;

import jakarta.validation.constraints.Size;

public class TelegramIdExistsException extends RuntimeException {
    public TelegramIdExistsException(@Size(min = 1) String telegramId) {
        super("Пользователь с telegramId " + telegramId + " уже существует.");
    }
}
