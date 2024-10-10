package net.akarmanov.projectplace.api.auth;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Ответ на аутентификацию.")
public class JwtAuthenticationResponse {
    @Schema(description = "Токен.")
    private String accessToken;

    @Schema(description = "Тип токена.")
    private String tokenType = "Bearer";
}
