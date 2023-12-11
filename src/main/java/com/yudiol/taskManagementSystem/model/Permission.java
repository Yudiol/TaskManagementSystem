package com.yudiol.taskManagementSystem.model;

public enum Permission {
    READ("READ"),
    CREATE("CREATE");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
