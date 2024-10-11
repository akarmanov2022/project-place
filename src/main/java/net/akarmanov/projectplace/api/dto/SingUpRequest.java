package net.akarmanov.projectplace.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Запрос на регистрацию.")
public class SingUpRequest {
    @Size(min = 4, max = 20, message = "Имя пользователя должно быть от 4 до 20 символов.")
    @Schema(description = "Имя пользователя.")
    @NotBlank(message = "Имя пользователя не может быть null.")
    private String username;

    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов.")
    @Schema(description = "Пароль.")
    @NotBlank(message = "Пароль не может быть null.")
    private String password;

    @Size(max = 255, message = "Имя должно быть не более 255 символов.")
    @Schema(description = "Имя.")
    private String firstName;

    @Size(max = 255, message = "Фамилия должна быть не более 255 символов.")
    @Schema(description = "Фамилия.")
    private String lastName;

    @Size(max = 255, message = "Отчество должно быть не более 255 символов.")
    @Schema(description = "Отчество.")
    private String middleName;

    @Pattern(regexp = "^\\+7\\d{10}$", message = "Номер телефона должен быть в формате +7XXXXXXXXXX.")
    @Schema(description = "Номер телефона.")
    @NotBlank(message = "Номер телефона не может быть null.")
    private String phoneNumber;

    @Size(max = 32, message = "Telegram ID должен быть не более 32 символов.")
    @Schema(description = "Telegram ID.")
    @NotBlank(message = "Telegram ID не может быть null.")
    private String telegramId;

    @Schema(description = "Роль.")
    @NotNull(message = "Роль не может быть null.")
    private UserRoleDto role;
}
