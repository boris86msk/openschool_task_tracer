package ru.openschool_task_tracer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.openschool_task_tracer.model.Task;
import ru.openschool_task_tracer.repository.TaskRepository;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskServiceTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void wenAddNewTask() {
        Task task = new Task();
        task.setTitle("some title");
        task.setDescription("some description");
        task.setDueDate(LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 0));
        task.setCompleted(false);

        Task savedTask = taskRepository.save(task);
        task.setId(savedTask.getId());
        assertThat(taskRepository.findById(savedTask.getId()).get()).isEqualTo(task);
    }
}