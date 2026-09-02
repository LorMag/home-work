package com.example.homework.service;

import com.example.homework.Priority;
import com.example.homework.Status;
import com.example.homework.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final Map<Long, Task> storage = new HashMap<>();

    public TaskService() {
        this.storage.put(1L, new Task(
                1L,
                1L,
                1L,
                Status.CREATED,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5),
                Priority.LOW
        ));
        this.storage.put(2L, new Task(
                2L,
                2L,
                2L,
                Status.IN_PROGRESS,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5),
                Priority.MEDIUM
        ));
        this.storage.put(3L, new Task(
                3L,
                3L,
                3L,
                Status.DONE,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5),
                Priority.HIGH
        ));
    }

    public Task getTaskById(Long id) {
        if (!storage.containsKey(id)) {
            throw new NoSuchElementException("Не существует задачи с id = " + id);
        }
        return storage.get(id);
    }

    public List<Task> getAllTasks() {
        return storage.values().stream().toList();
    }

}
