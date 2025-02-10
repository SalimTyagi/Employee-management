package org.data.enums;

public enum RoleType {
    ADMIN("Admin"),
    EMPLOYEE("Employee"),
    MANAGER("Manager");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName; // Returns "Admin", "Employee", "Manager"
    }
}
