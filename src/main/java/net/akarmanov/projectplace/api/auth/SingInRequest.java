package net.akarmanov.projectplace.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Запрос на аутентификацию.")
public class SingInRequest {
    @Schema(description = "Имя пользователя.")
    @Size(min = 4, max = 20, message = "Имя пользователя должно быть от 4 до 20 символов.")
    @NotBlank(message = "Имя пользователя не может быть null.")
    private String username;

    @Schema(description = "Пароль.")
    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов.")
    @NotBlank(message = "Пароль не может быть null.")
    private String password;
}
