package net.akarmanov.projectplace.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.akarmanov.projectplace.api.user.UserRoleDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String telegramId;
    @NotNull
    private UserRoleDto role = UserRoleDto.TRACKER;
}
