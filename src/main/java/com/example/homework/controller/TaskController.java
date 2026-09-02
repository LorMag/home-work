package com.example.homework.controller;

import com.example.homework.Task;
import com.example.homework.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
