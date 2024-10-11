package net.akarmanov.projectplace.api.dto;

public enum UserRoleDto {
    ADMIN,
    TRACKER,
    SUPERADMIN;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
