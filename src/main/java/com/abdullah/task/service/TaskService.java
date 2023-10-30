package com.abdullah.task.service;

import com.abdullah.task.dto.TaskRegisterRequestDTO;
import com.abdullah.task.dto.TaskUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    ResponseEntity<?> saveTask(TaskRegisterRequestDTO taskRegisterRequestDTO);

    ResponseEntity<?> loadTasks();

    ResponseEntity<?> updateTask(Long id, TaskUpdateRequestDTO taskUpdateRequestDTO);

    ResponseEntity<?> deleteTask(Long id);
}
