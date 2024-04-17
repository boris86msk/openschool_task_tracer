package ru.openschool_task_tracer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.openschool_task_tracer.controller.TaskController;
import ru.openschool_task_tracer.model.Task;
import ru.openschool_task_tracer.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimpleTaskServiceTest {
    public TaskService taskService;
    public TaskRepository taskRepository;

    @BeforeEach
    public void initService() {
        taskRepository = mock(TaskRepository.class);
        taskService = new SimpleTaskService(taskRepository);
    }

    @Test
    void wenSaveTask() {
        Task task = mock(Task.class);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.saveTask(task);
        verify(taskRepository).save(task);
        assertThat(result).isEqualTo(task);
    }

    @Test
    void wenGetAllTasks() {
        Task task = mock(Task.class);
        Task task2 = mock(Task.class);
        List<Task> tasksList = List.of(task, task2);
        when(taskRepository.findAll()).thenReturn(tasksList);
        List<Task> resultList = taskService.getAllTask();
        verify(taskRepository).findAll();
        assertThat(resultList).isEqualTo(tasksList);
    }

    @Test
    void wenGetTaskById() {
        Task task = mock(Task.class);
        Long someTaskId = 2L;
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        when(taskRepository.findById(idCapture.capture())).thenReturn(Optional.of(task));

        Optional<Task> taskById = taskService.getTaskById(someTaskId);
        verify(taskRepository).findById(someTaskId);
        Long value = idCapture.getValue();
        assertThat(value).isEqualTo(someTaskId);
        assertThat(taskById.get()).isEqualTo(task);
    }

    @Test
    void wenUpdateTaskById() {
        Long someTaskId = 2L;
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(taskRepository).update(idCapture.capture());

        taskService.updateTaskById(someTaskId);
        verify(taskRepository).update(any(Long.class));
        assertThat(idCapture.getValue()).isEqualTo(someTaskId);
    }

    @Test
    void wenDeleteTaskById() {
        Long someTaskId = 2L;
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(taskRepository).deleteById(idCapture.capture());

        taskService.deleteTaskById(someTaskId);
        verify(taskRepository).deleteById(any(Long.class));
        assertThat(idCapture.getValue()).isEqualTo(someTaskId);
    }

}