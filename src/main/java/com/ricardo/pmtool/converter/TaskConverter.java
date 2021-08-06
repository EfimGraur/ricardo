package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public TaskData convert(final Task task) {
        TaskData taskData = new TaskData();
        taskData.setId(task.getId());
        taskData.setProgress(task.getProgress());
        if (task.getUser() != null) {
            taskData.setAssignee(task.getUser().getUsername());
        }
        taskData.setDescription(task.getDescription());
        if (task.getProject() != null) {
            taskData.setProject(task.getProject().getName());
            taskData.setPm(task.getProject().getUser().getUsername());
        }
        return taskData;
    }
}
