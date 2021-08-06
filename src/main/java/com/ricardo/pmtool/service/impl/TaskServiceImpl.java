package com.ricardo.pmtool.service.impl;

import com.ricardo.pmtool.converter.TaskConverter;
import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Task;
import com.ricardo.pmtool.persistence.repository.TaskRepository;
import com.ricardo.pmtool.service.TaskService;
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
        taskRepository.deleteById(id);
    }
}
