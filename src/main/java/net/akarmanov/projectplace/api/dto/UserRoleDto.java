package net.akarmanov.projectplace.api.dto;

public enum UserRoleDto {
    ADMIN,
    TRACKER,
    SUPER_ADMIN;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
