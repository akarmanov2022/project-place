package net.akarmanov.projectplace.persistence.entities;

import lombok.Getter;

@Getter
public enum MeetingStatus {
    OK(1),
    WITH_ISSUES(0.5),
    MANY_ISSUES(0.25);

    private final double value;

    MeetingStatus(double value) {
        this.value = value;
    }

}
