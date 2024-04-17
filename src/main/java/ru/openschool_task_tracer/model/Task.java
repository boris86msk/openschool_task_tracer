package ru.openschool_task_tracer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @EqualsAndHashCode.Include
    private String title;

    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    private boolean completed;

    /**
     * constructor for tests
     */
    public Task(String someTitle, String someDescription, LocalDateTime date) {
        this.title = someTitle;
        this.description = someDescription;
        this.dueDate = date;
    }
}
