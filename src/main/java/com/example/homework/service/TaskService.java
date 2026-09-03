package com.example.homework.service;

import com.example.homework.Status;
import com.example.homework.Task;
import com.example.homework.TaskMapper;
import com.example.homework.repository.TaskRepository;
import com.example.homework.repository.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public Task getTaskById(Long id) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Не существует задачи с id = " + id)
        );
        return taskMapper.toResponse(taskEntity);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::toResponse).toList();
    }

    public Task createTask(Task task) {
        if (task.getId() != null && task.getStatus() != null) {
            throw new IllegalArgumentException("id и status при создании должны быть пустыми.");
        }

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCreatorId(task.getCreatorId());
        taskEntity.setAssignedUserId(task.getAssignedUserId());
        taskEntity.setStatus(Status.CREATED);
        taskEntity.setCreateDateTime(task.getCreateDateTime());
        taskEntity.setDeadlineDate(task.getDeadlineDate());
        taskEntity.setPriority(task.getPriority());

        return taskMapper.toResponse(taskRepository.save(taskEntity));
    }

    public Task updateTask(Long id, Task task) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Не существует задачи с id = " + id)
        );

        if (task.getId() != null) {
            throw new IllegalArgumentException("id при обновлении должен быть пустым.");
        }

        if (taskEntity.getStatus() == Status.DONE && task.getStatus() != Status.IN_PROGRESS) {
            throw new IllegalArgumentException("Обновить задачу в стаусе DONE можно только при её переводе в IN_PROGRESS.");
        }

        taskEntity.setCreatorId(task.getId());
        taskEntity.setAssignedUserId(task.getAssignedUserId());
        taskEntity.setStatus(task.getStatus());
        taskEntity.setDeadlineDate(task.getDeadlineDate());
        taskEntity.setPriority(task.getPriority());

        return taskMapper.toResponse(taskRepository.save(taskEntity));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new NoSuchElementException("Не существует задачи с id = " + id);
        }
        taskRepository.deleteById(id);
    }

    public Task setTaskStatusInProgress(Long id, Task task) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Не существует задачи с id = " + id)
        );

        if (taskEntity.getStatus() == Status.IN_PROGRESS) {
            throw new IllegalArgumentException("Задача уже IN_PROGRESS");

        }

        if (task.getAssignedUserId() == null) {
            throw new IllegalArgumentException("Невозможно перевести задачу в IN_PROGRESS, AssignedUserId = null");
        }

        if (taskRepository.findTasksByAssignedUserIdAndStatus(task.getAssignedUserId(), Status.IN_PROGRESS) > 6) {
            throw new IllegalArgumentException(
                    "Невозможно перевести задачу в IN_PROGRESS, у пользователя с id = " + task.getAssignedUserId() + " активно пять задач."
            );
        }

        taskEntity.setStatus(Status.IN_PROGRESS);
        return taskMapper.toResponse(taskRepository.save(taskEntity));
    }
}
