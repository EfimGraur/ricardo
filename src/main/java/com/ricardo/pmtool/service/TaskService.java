package com.ricardo.pmtool.service;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskData> getAllTask();

    TaskData getTaskById(Long id);

    Long createTask(TaskData taskData);

    void deleteById(Long id);

    void updateTask(TaskData taskData, Long taskId);
}
