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
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> storage = new HashMap<>();

    private final AtomicLong counter = new AtomicLong(1L);

    public TaskService() {
        this.storage.put(counter.get(),
                new Task(
                        counter.getAndIncrement(),
                        1L,
                        1L,
                        Status.CREATED,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(5),
                        Priority.LOW
                ));
        this.storage.put(counter.get(),
                new Task(
                        counter.getAndIncrement(),
                        2L,
                        2L,
                        Status.IN_PROGRESS,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(5),
                        Priority.MEDIUM
                ));
        this.storage.put(counter.get(),
                new Task(
                        counter.getAndIncrement(),
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

    public Task createTask(Task task) {
        if (task.getId() != null && task.getStatus() != null) {
            throw new IllegalArgumentException("id и status при создании должны быть пустыми.");
        }

        task.setId(counter.getAndIncrement());
        task.setStatus(Status.CREATED);
        this.storage.put(task.getId(), task);
        return task;
    }

    public Task updateTask(Long id, Task task) {
        if (!this.storage.containsKey(id)) {
            throw new NoSuchElementException("Не существует задачи с id = " + id);
        }

        if (task.getId() != null) {
            throw new IllegalArgumentException("id при обновлении должен быть пустым.");
        }

        Task updatedTask = this.storage.get(id);

        if (updatedTask.getStatus() == Status.DONE && task.getStatus() != Status.IN_PROGRESS) {
            throw new IllegalArgumentException("Обновить задачу в стаусе DONE можно только при её переводе в IN_PROGRESS.");
        }

        updatedTask.setCreatorId(task.getId());
        updatedTask.setAssignedUserId(task.getAssignedUserId());
        updatedTask.setStatus(task.getStatus());
        updatedTask.setDeadlineDate(task.getDeadlineDate());
        updatedTask.setPriority(task.getPriority());
        return updatedTask;
    }

    public void deleteTask(Long id) {
        if (!this.storage.containsKey(id)) {
            throw new NoSuchElementException("Не существует задачи с id = " + id);
        }
        this.storage.remove(id);
    }

}
