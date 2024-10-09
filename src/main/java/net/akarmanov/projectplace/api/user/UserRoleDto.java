package net.akarmanov.projectplace.api.user;

public enum UserRoleDto {
    ADMIN,
    TRACKER,
    SUPERADMIN;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
