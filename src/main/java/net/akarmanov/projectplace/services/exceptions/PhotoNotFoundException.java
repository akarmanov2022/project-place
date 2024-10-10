package net.akarmanov.projectplace.services.exceptions;

import java.util.UUID;

public class PhotoNotFoundException extends RuntimeException {
    public PhotoNotFoundException(UUID photoId) {
        super("Фотография пользователя с ID " + photoId + " не найдена!");
    }
}
