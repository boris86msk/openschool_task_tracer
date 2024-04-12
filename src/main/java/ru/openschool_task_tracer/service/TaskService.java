package ru.openschool_task_tracer.service;

import ru.openschool_task_tracer.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task saveTask(Task task);
    List<Task> getAllTask();
    Optional<Task> getTaskById(long id);
    void updateTaskById(long id);
    void deleteTaskById(long id);
}
