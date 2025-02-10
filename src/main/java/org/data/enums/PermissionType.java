package org.data.enums;

public enum PermissionType {
    EDIT("Edit"),
    VIEW("View"),
    DELETE("Delete"),
    CREATE("Create");

    private final String displayName;

    PermissionType(String displayName) {
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
