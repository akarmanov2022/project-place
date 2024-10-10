package net.akarmanov.projectplace.services.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String username) {
        super("Пользователь с именем " + username + " уже существует.");
    }
}
