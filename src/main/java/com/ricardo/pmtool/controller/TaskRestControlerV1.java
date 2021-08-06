package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestControlerV1 {

    private final TaskService taskService;

    public TaskRestControlerV1(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskData> getAll() {
        return taskService.getAllTask();
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('tasks:read')")
//    public Task getById(@PathVariable Long id) {
//        return taskRepository.getById(id);
//    }

    //    @PostMapping
//    @PreAuthorize("hasAuthority('tasks:write')")
//    public Task create(@RequestBody Task task) {
//        taskRepository.save(task);
//        return task;
//    }
//
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
