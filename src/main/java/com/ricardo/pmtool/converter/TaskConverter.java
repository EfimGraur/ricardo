package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Project;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.persistence.repository.ProjectRepository;
import com.ricardo.pmtool.persistence.repository.UserRepository;
import com.ricardo.pmtool.status.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskConverter(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public TaskData convert(final Task task) {
        TaskData taskData = new TaskData();
        taskData.setId(task.getId());
        taskData.setProgress(task.getProgress());
        if (task.getStatus() != null) {
            taskData.setStatus(task.getStatus().toString());
        }
        if (task.getUser() != null) {
            taskData.setAssignee(task.getUser().getUsername());
        }
        taskData.setDescription(task.getDescription());
        if (task.getProject() != null) {
            taskData.setProjectCode(task.getProject().getCode());
            taskData.setPm(task.getProject().getUser().getUsername());
        }
        return taskData;
    }

    public Task convert(final TaskData taskData) {
        final Task task = new Task();
        task.setDescription(taskData.getDescription());
        task.setProgress(taskData.getProgress());
        task.setStatus(TaskStatus.valueOf(taskData.getStatus()));

        if (!taskData.getAssignee().equals(""))  {
            User user = userRepository.findByUsername(taskData.getAssignee()).orElseThrow(() -> {
                throw new UsernameNotFoundException("Username " + taskData.getAssignee() + " not found");
            });
            task.setUser(user);
        }

        if (!taskData.getProjectCode().equals("")) {
            Project project = projectRepository.findByCode(taskData.getProjectCode()).orElseThrow(() -> {
                throw new UsernameNotFoundException("Project with code: " + taskData.getProjectCode() + " not found");
            });
            task.setProject(project);
        }

        return task;
    }
}
