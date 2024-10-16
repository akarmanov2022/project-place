package net.akarmanov.projectplace.rest.api.dto;

import lombok.Builder;

@Builder
public record UserUpdateDTO(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String telegramId) {
}
