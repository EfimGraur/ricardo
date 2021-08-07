package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.converter.TaskConverter;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.persistence.repository.TaskRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.service.TaskService;
import com.ricardo.pmtool.status.TaskStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TaskData> getAllTask() {
        return taskRepository.findAll().stream().map(taskConverter::convert).collect(Collectors.toList());
    }

    @Override
    public TaskData getTaskById(Long id) {
        return taskConverter.convert(taskRepository.getById(id));
    }

    @Override
    public Long createTask(TaskData taskData) {
        final Task task = taskConverter.convert(taskData);
        return taskRepository.save(task).getId();
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(TaskData taskData, Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> {
            throw new UsernameNotFoundException("Task with ID: " + taskId + "not found");
        });
        task.setDescription(taskData.getDescription());
        task.setProgress(taskData.getProgress());
        task.setStatus(TaskStatus.valueOf(taskData.getStatus()));

        //un-assign task
        if (taskData.getAssignee() == null || taskData.getAssignee().equals("")) {
            task.setUser(null);
        } else {
            final User user = userRepository.findByUsername(taskData.getAssignee()).get();
            task.setUser(user);
        }

        //remove task from the project
        if (taskData.getProjectCode() == null || taskData.getProjectCode().equals("")) {
            task.setProject(null);
        } else {
            final Project project = projectRepository.findByCode(taskData.getProjectCode()).get();
            task.setProject(project);
        }

        taskRepository.save(task);
    }
}
