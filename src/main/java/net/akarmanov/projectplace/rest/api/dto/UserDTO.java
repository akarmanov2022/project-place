package net.akarmanov.projectplace.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String telegramId;

    private UserRoleDto role;
}
