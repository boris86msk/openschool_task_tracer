package ru.openschool_task_tracer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.openschool_task_tracer.model.Task;
import ru.openschool_task_tracer.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        Optional<Task> taskById = taskService.getTaskById(id);
        if(taskById.isEmpty()) {
            throw new RuntimeException();
        }
        return taskById.get();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public void taskUpdate(@PathVariable long id) {
        taskService.updateTaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
    }

}
