package net.akarmanov.projectplace.rest.api.dto;

import lombok.Builder;
import net.akarmanov.projectplace.models.UserRole;
import net.akarmanov.projectplace.services.user.UserPhotoDto;

import java.util.UUID;

@Builder
public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String telegramId,
        UserRole role,
        UserPhotoDto photo,
        boolean enabled) {
}
