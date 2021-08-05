package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.data.TaskData;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public TaskData convert(final Task task) {
        final TaskData taskData = new TaskData();
        taskData.setId(task.getId());
        taskData.setProgress(task.getProgress());
        taskData.setAssignee(task.getUser().getUsername());
        taskData.setDescription(task.getDescription());
        taskData.setProject(task.getProject().getName());
        taskData.setPm(task.getProject().getUser().getUsername());

        return taskData;
    }
}
