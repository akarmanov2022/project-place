package net.akarmanov.projectplace.models;

import lombok.Builder;

@Builder
public record ExceptionResponseModel(String message) {
}
