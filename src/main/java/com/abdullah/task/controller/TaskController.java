package com.abdullah.task.controller;

import com.abdullah.task.dto.TaskRegisterRequestDTO;
import com.abdullah.task.dto.TaskUpdateRequestDTO;
import com.abdullah.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTask(@RequestBody TaskRegisterRequestDTO taskRegisterRequestDTO) {
        return taskService.saveTask(taskRegisterRequestDTO);
    }

    @GetMapping("/load")
    public ResponseEntity<?> loadTasks() {
        return taskService.loadTasks();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") Long id, @RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO) {
        return taskService.updateTask(id, taskUpdateRequestDTO);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        return taskService.deleteTask(id);
    }
}