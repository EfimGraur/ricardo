package net.proselyte.springsecuritydemo.controller;

import net.proselyte.springsecuritydemo.data.ProjectData;
import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.data.UserData;
import net.proselyte.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<UserData> getAll() {
        return userService.getUsers();
    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:read')")
//    public User getById(@PathVariable Long id) {
//        return userRepository.getById(id);
//    }

    @GetMapping("/{userId}/projects/{projectId}")
    @PreAuthorize("hasAuthority('projects:read')")
    public ProjectData getById(@PathVariable Long userId, @PathVariable Long projectId) {
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

//    @PostMapping
//    @PreAuthorize("hasAuthority('users:write')")
//    public User create(@RequestBody User user) {
//        userRepository.save(user);
//        return user;
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('users:write')")
//    public void deleteById(@PathVariable Long id) {
//        userRepository.deleteById(id);
//    }
}
