package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.converter.ProjectConverter;
import com.ricardo.pmtool.converter.TaskConverter;
import com.ricardo.pmtool.converter.UserConverter;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.TaskRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.roles.Role;
import com.ricardo.pmtool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ricardo.pmtool.constants.GenericConstants.ALL_USERS_QUERY_PARAM;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private final UserConverter userConverter;
    private final ProjectConverter projectConverter;
    private final TaskConverter taskConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, UserConverter userConverter, ProjectConverter projectConverter, TaskConverter taskConverter) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userConverter = userConverter;
        this.projectConverter = projectConverter;
        this.taskConverter = taskConverter;
    }

    public ProjectData getProject(long userId, long projectId) {
        List<Project> userProjects = userRepository.getById(userId).getProjects();
        return userProjects.stream().filter(project -> project.getId() == projectId).map(projectConverter::convert).findFirst().orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public List<ProjectData> getAllProjectsByUser(long userId) {
        List<Project> userProjects = userRepository.getById(userId).getProjects();
        return userProjects.stream().filter(project -> project.getUser().getId() == userId).map(projectConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<TaskData> getAllTasksByPM(long userId) {
        List<Project> userProjects = userRepository.getById(userId).getProjects();
        return userProjects.stream().flatMap(user -> user.getTasks().stream()).map(taskConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<TaskData> getAllTasksByUser(long userId) {
        List<Task> userTasks = userRepository.getById(userId).getTasks();
        List<Task> unassignedTasks = taskRepository.findAllUnassignedTasks();
        return Stream.concat(userTasks.stream(), unassignedTasks.stream()).map(taskConverter::convert).collect(Collectors.toList());
    }


    @Override
    public List<UserData> getAllUsers(String role) {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> {
                    if (!role.equals(ALL_USERS_QUERY_PARAM)) {
                        return user.getRole().equals(Role.valueOf(role));
                    } else {
                        return true;
                    }
                })
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public UserData getUserById(long userId) {
        return userConverter.convert(userRepository.getById(userId));
    }

    @Override
    public void deleteUserById(long userId) {
        User user = userRepository.getById(userId);
        user.getProjects().forEach(a -> a.setUser(null));
        userRepository.deleteById(userId);
    }

    @Override
    public Long createUser(UserData userData) {
        final User user = userConverter.convert(userData);
        return userRepository.save(user).getId();
    }

    @Override
    public void updateUser(UserData userData, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> {
            throw new UsernameNotFoundException("User with ID "+userId+ " not found");
        });
        user.setRole(Role.valueOf(userData.getRole()));
        user.setEmail(userData.getEmail());
        user.setUsername(userData.getUsername());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());

        userRepository.save(user);
    }

}
