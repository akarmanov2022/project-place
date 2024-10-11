package net.akarmanov.projectplace.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String telegramId;

    private UserRoleDto role;
}
