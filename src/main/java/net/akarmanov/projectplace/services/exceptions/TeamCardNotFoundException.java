package net.akarmanov.projectplace.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamCardNotFoundException extends RuntimeException {
    public TeamCardNotFoundException(UUID teamCardId) {
        super("Карточка команды с идентификатором " + teamCardId + " не найдена");
    }
}
