package com.ricardo.pmtool.roles;

import com.ricardo.pmtool.permissions.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(
            Permission.PROJECTS_READ,
            Permission.PROJECTS_WRITE,
            Permission.TASKS_READ,
            Permission.TASKS_WRITE,
            Permission.USERS_READ,
            Permission.USERS_WRITE
    )),
    PM(Set.of(
            Permission.PROJECTS_READ,
            Permission.PROJECTS_WRITE,
            Permission.TASKS_READ,
            Permission.TASKS_WRITE,
            Permission.USERS_READ)
    ),
    DEV(Set.of(
            Permission.TASKS_READ,
            Permission.TASKS_WRITE)
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
