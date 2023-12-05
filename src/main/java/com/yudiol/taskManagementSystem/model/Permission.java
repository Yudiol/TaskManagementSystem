package com.yudiol.taskManagementSystem.model;

public enum Permission {
    READ("READ"),
    CREATE("CREATE"),
    EDIT("EDIT"),
    CHANGE_TASK_STATUS("CHANGE_TASK_STATUS");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
