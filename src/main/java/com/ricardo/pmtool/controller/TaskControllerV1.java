package com.ricardo.pmtool.controller;

import com.ricardo.pmtool.data.TaskData;
import com.ricardo.pmtool.service.TaskService;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.ricardo.pmtool.constants.RequestMappings.TASKS_URL;

@RestController
@RequestMapping(TASKS_URL)
public class TaskControllerV1 {

    private final TaskService taskService;

    public TaskControllerV1(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskData> getAllTaks() {
        return taskService.getAllTask();
    }

    @GetMapping({"/{taskId}"})
    public TaskData getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('tasks:write')")
    public ResponseEntity<Object> createTask(@RequestBody TaskData taskData) {
        try {
            Long taskId = taskService.createTask(taskData);
            URI newUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/api/v1/tasks/{id}")
                    .buildAndExpand(taskId)
                    .toUri();

            return ResponseEntity.created(newUserLocation).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public ResponseEntity<Object> updateTask(@PathVariable Long taskId, @RequestBody TaskData taskData) {
        try {
            taskService.updateTask(taskData, taskId);

            return ResponseEntity.noContent().build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('tasks:write')")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
