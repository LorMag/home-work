package com.example.homework.controller;

import com.example.homework.Task;
import com.example.homework.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("getAllTasks called");
        List<Task> tasks = taskService.getAllTasks();
        log.info("getAllTasks finished: {}", tasks.stream().map(Task::toString).collect(Collectors.joining("\n")));
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        log.info("getTaskById called, id = {}", id);
        Task task = taskService.getTaskById(id);
        log.info("getTaskById finished: {}", task);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        log.info("createTask called");
        Task createdTask = taskService.createTask(task);
        log.info("createTask finished, task = {}", createdTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        log.info("updateTask called, id = {}", id);
        Task updatedTask = taskService.updateTask(id, task);
        log.info("updateTask finished, task = {}", updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("deleteTask called, id = {}", id);
        taskService.deleteTask(id);
        log.info("deleteTask finished");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Task> setTaskStatusInProgress(@PathVariable("id") Long id, @RequestBody Task task)  {
        log.info("setTaskStatusInProgress called, id = {}, task = {}", id, task);
        Task updatedTask = taskService.setTaskStatusInProgress(id, task);
        log.info("setTaskStatusInProgress finished, response = {}", updatedTask);
        return ResponseEntity.ok(updatedTask);
    }
}
