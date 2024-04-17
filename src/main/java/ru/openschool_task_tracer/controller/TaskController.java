package ru.openschool_task_tracer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.openschool_task_tracer.model.Task;
import ru.openschool_task_tracer.service.TaskService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        if (taskById.isEmpty()) {
            throw new RuntimeException(String.format("Запись с id=%d не найдеа", id));
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

    @ExceptionHandler(value = { DataIntegrityViolationException.class, IllegalArgumentException.class, RuntimeException.class })
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, String> details = new HashMap<>();
        details.put("message", e.getMessage());
        details.put("type", String.valueOf(e.getClass()));
        details.put("timestamp", String.valueOf(LocalDateTime.now()));
        details.put("path", request.getRequestURI());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(details));
    }

}
