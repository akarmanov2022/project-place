package net.akarmanov.projectplace.rest.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Schema(description = "DTO для обновления данных пользователя")
@Builder
public record UserUpdateDTO(
        @NotBlank(message = "Имя не может быть пустым")
        String firstName,
        @NotBlank(message = "Фамилия не может быть пустой")
        String lastName,
        @NotBlank(message = "Отчество не может быть пустым")
        String middleName,
        @Schema(description = "Номер телефона в формате +7XXXXXXXXXX", example = "+71234567890")
        @Pattern(regexp = "^\\+7\\d{10}$", message = "Номер телефона должен быть в формате +7XXXXXXXXXX")
        @NotBlank(message = "Номер телефона не может быть пустым")
        String phoneNumber,
        @NotBlank(message = "Telegram ID не может быть пустым")
        String telegramId) {
}
