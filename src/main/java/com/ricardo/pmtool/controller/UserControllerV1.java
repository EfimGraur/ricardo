package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.service.UserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.ricardo.pmtool.constants.GenericConstants.ALL_USERS_QUERY_PARAM;
import static com.ricardo.pmtool.constants.RequestMappings.USERS_URL;
import static com.ricardo.pmtool.constants.RequestMappings.USERS_URL_BY_ID;

@RestController
@RequestMapping(USERS_URL)
public class UserControllerV1 {

    private final UserService userService;

    @Autowired
    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<UserData> getAll(@RequestParam(defaultValue = ALL_USERS_QUERY_PARAM) String role) {
        return userService.getAllUsers(role);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public UserData getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/{userId}/projects/{projectId}")
    @PreAuthorize("hasAuthority('projects:read')")
    public ProjectData getProjectByUserId(@PathVariable Long userId, @PathVariable Long projectId) {
        return userService.getProject(userId, projectId);
    }

    @GetMapping("/{userId}/projects")
    @PreAuthorize("hasAuthority('projects:read')")
    public List<ProjectData> getAllProjectsByUser(@PathVariable Long userId) {
        return userService.getAllProjectsByUser(userId);
    }

    @GetMapping("/{userId}/projects/tasks")
    @PreAuthorize("hasAuthority('tasks:read')")
    public List<TaskData> getAllTasksByPM(@PathVariable Long userId) {
        return userService.getAllTasksByPM(userId);
    }

    @GetMapping("/{userId}/tasks")
    @PreAuthorize("hasAuthority('tasks:read')")
    public List<TaskData> getAllTasksByUser(@PathVariable Long userId) {
        return userService.getAllTasksByUser(userId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Object> createUser(@RequestBody UserData userData) {
        try {
            Long userId = userService.createUser(userData);

            URI newUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path(USERS_URL_BY_ID)
                    .buildAndExpand(userId)
                    .toUri();

            return ResponseEntity.created(newUserLocation).build();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody UserData userData) {
        try {
            userService.updateUser(userData, userId);

            return ResponseEntity.noContent().build();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.SC_CONFLICT).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
