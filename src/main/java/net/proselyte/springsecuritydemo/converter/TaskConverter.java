package net.proselyte.springsecuritydemo.converter;

import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.persistence.model.Task;
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
