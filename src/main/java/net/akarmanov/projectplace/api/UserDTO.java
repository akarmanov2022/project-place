package net.akarmanov.projectplace.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
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
    private String photo;
    @NotBlank
    private String role;

}
