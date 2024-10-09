package net.akarmanov.projectplace.models;

public enum UserRole {
    ADMIN,
    SUPER_ADMIN,
    TRACKER;


    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
