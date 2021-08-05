package net.proselyte.springsecuritydemo.service;

import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.persistence.model.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {
    List<TaskData> getAllTask();

    TaskData getById(Long id);

    TaskData create(Task task);

    void deleteById(Long id);
}
