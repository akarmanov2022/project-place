package net.akarmanov.projectplace.models;

import lombok.Builder;

/**
 * Общая модель ответа на исключение.
 *
 * @param message Сообщение об ошибке.
 */
@Builder
public record ExceptionResponseModel(String message) {
}
