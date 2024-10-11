package net.akarmanov.projectplace.services.exceptions;

import java.util.UUID;

public class TrackerExistsException extends RuntimeException {
    public TrackerExistsException(UUID userId) {
        super("Трекер с userId " + userId + " уже существует");
    }
}
