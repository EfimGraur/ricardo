package net.proselyte.springsecuritydemo.permissions;

public enum Permission {
    PROJECTS_READ("projects:read"),
    PROJECTS_WRITE("projects:write"),
    TASKS_READ("tasks:read"),
    TASKS_WRITE("tasks:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
