package ru.openschool_task_tracer.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.openschool_task_tracer.model.Task;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void wenAddNewTask() {
        Task task = new Task();
        task.setTitle("some title");
        task.setDescription("some description");
        task.setDueDate(LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 0));

        task.setId(taskRepository.save(task).getId());
        assertThat(taskRepository.findById(task.getId()).get()).isEqualTo(task);
    }

    @Test
    public void wenGetAllTasks() {
        Task task = new Task();
        task.setTitle("some title");
        task.setDescription("some description");
        task.setDueDate(LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 0));

        Task task2 = new Task();
        task2.setTitle("some title2");
        task2.setDescription("some description2");
        task2.setDueDate(LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 10));

        taskRepository.save(task);
        taskRepository.save(task2);

        List<Task> expectedList = List.of(task, task2);
        assertThat(taskRepository.findAll()).isEqualTo(expectedList);
    }

    @Test
    public void wenDeleteTask() {
        Task task = new Task();
        task.setTitle("some title");
        task.setDescription("some description");
        task.setDueDate(LocalDateTime.of(2024, Month.JANUARY, 5, 12, 0, 0));

        task.setId(taskRepository.save(task).getId());
        taskRepository.deleteById(task.getId());
        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
}