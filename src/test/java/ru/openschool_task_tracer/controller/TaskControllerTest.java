package ru.openschool_task_tracer.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.openschool_task_tracer.model.Task;
import ru.openschool_task_tracer.service.TaskService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TaskControllerTest {
    public TaskController taskController;
    public TaskService taskService;

    @BeforeEach
    public void initService() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    @Test
    void wenGetAllTasks() {
        Task task = new Task("some title", "some description", LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 0));
        Task task2 = new Task("some title2", "some description2", LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 01));
        when(taskService.getAllTask()).thenReturn(List.of(task, task2));

        var expectedList = List.of(task, task2);
        assertThat(taskController.getAllTasks()).isEqualTo(expectedList);
    }

    @Test
    void wenCreateNewTask() {
        Task task = new Task("some title", "some description", LocalDateTime.of(2024,
                Month.JANUARY, 5, 12, 0, 0));
        Task saveTask = new Task(1, "some title", "some description", LocalDateTime.of(2024, Month.JANUARY,
                5, 12, 0, 0), false);
        when(taskService.saveTask(task)).thenReturn(saveTask);

        var expectedTask = taskController.createTask(task);
        assertThat(expectedTask.getId()).isGreaterThan(0);
        assertThat(expectedTask.isCompleted()).isFalse();
    }

    @Test
    void wenGetTaskById() {
        Task task = new Task(1, "some title", "some description", LocalDateTime.of(2024,
                Month.JANUARY, 5, 12, 0, 0), false);
        when(taskService.getTaskById(task.getId())).thenReturn(Optional.of(task));
        Task taskById = taskController.getTaskById(task.getId());
        assertThat(taskById).isEqualTo(task);
    }

    @Test
    void wenNeedUpdateTaskById() {
        Long someTaskId = 2L;
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(taskService).updateTaskById(idCapture.capture());

        taskController.taskUpdate(someTaskId);
        verify(taskService).updateTaskById(someTaskId);
        Long value = idCapture.getValue();
        assertThat(value).isEqualTo(someTaskId);
    }

    @Test
    void wenDeleteTaskById() {
        Long someTaskId = 1L;
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(taskService).deleteTaskById(idCapture.capture());

        taskController.deleteTaskById(someTaskId);
        verify(taskService).deleteTaskById(someTaskId);
        Long value = idCapture.getValue();
        assertThat(value).isEqualTo(someTaskId);
    }

}