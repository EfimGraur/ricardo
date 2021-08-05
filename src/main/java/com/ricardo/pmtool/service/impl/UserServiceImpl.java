package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.converter.ProjectConverter;
import com.ricardo.pmtool.converter.TaskConverter;
import com.ricardo.pmtool.converter.UserConverter;
import com.ricardo.pmtool.data.ProjectData;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;
    private final ProjectConverter projectConverter;
    private final TaskConverter taskConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, ProjectConverter projectConverter, TaskConverter taskConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.projectConverter = projectConverter;
        this.taskConverter = taskConverter;
    }

    //    @Autowired
//    ProjectRepository projectRepository;

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
        return userTasks.stream().map(taskConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<UserData> getUsers() {
        return userRepository.findAll().stream().map(userConverter::convert).collect(Collectors.toList());
    }
}
