package net.akarmanov.projectplace.services.exceptions;

import java.util.UUID;

public class TrackerNotFoundException extends RuntimeException {
    public TrackerNotFoundException(UUID userId) {
        super("Трекер с userId " + userId + " не найден");
    }
}
