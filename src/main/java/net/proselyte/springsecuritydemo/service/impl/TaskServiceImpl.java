package net.proselyte.springsecuritydemo.service.impl;

import net.proselyte.springsecuritydemo.converter.TaskConverter;
import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.persistence.model.Task;
import net.proselyte.springsecuritydemo.persistence.repository.TaskRepository;
import net.proselyte.springsecuritydemo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
    }


    @Override
    public List<TaskData> getAllTask() {
        return taskRepository.findAll().stream().map(taskConverter::convert).collect(Collectors.toList());
    }

    @Override
    public TaskData getById(Long id) {
        return null;
    }

    @Override
    public TaskData create(Task task) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
