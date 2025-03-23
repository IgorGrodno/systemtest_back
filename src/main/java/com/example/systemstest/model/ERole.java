package com.example.systemstest.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ERole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String roleName;

    ERole(String roleName) {
        this.roleName = roleName;
    }

    @JsonValue
    public String getRoleName() {
        return roleName;
    }

    public static ERole fromString(String roleName) {
        for (ERole role : ERole.values()) {
            if (role.roleName.equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + roleName);
    }
}