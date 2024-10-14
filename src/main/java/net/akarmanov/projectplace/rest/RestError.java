package net.akarmanov.projectplace.rest;

import lombok.Builder;

import java.util.List;

@Builder
public record RestError(
        String code,
        String message,
        List<String> errors
) {
}
