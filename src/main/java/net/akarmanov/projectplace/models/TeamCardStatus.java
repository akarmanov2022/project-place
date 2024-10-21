package net.akarmanov.projectplace.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamCardStatus {
    OK("Все ок"),
    HAS_ISSUES("Есть проблемы"),
    HAS_MAJOR_ISSUES("Есть серьезные проблемы");

    private final String description;
}
