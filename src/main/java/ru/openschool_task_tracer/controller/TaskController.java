package ru.openschool_task_tracer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.openschool_task_tracer.model.Task;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return List.of(new Task());
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable long id) {
        return new Task();
    }

    @PostMapping("/tasks")
    public void createTask(@ModelAttribute Task task) {

    }

    @PutMapping("/tasks/{id}")
    public void taskUpdate(@PathVariable long id) {

    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable long id) {}

}
