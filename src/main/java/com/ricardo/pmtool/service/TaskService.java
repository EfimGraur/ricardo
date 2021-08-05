package com.ricardo.pmtool.service;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.persistence.model.Task;

import java.util.List;

public interface TaskService {
    List<TaskData> getAllTask();

    TaskData getById(Long id);

    TaskData create(Task task);

    void deleteById(Long id);
}
