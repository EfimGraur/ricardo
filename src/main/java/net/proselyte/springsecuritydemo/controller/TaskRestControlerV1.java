package net.proselyte.springsecuritydemo.controller;

import net.proselyte.springsecuritydemo.data.TaskData;
import net.proselyte.springsecuritydemo.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('tasks:write')")
//    public void deleteById(@PathVariable Long id) {
//        taskRepository.deleteById(id);
//    }
}
