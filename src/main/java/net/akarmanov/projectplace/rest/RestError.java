package net.akarmanov.projectplace.rest;

import lombok.Builder;

@Builder
public record RestError(
        String code,
        String message
) {
}
