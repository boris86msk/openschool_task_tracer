package ru.openschool_task_tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.openschool_task_tracer.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
