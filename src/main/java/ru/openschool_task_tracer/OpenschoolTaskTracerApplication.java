package ru.openschool_task_tracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OpenschoolTaskTracerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenschoolTaskTracerApplication.class, args);
    }

}
