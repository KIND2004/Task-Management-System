package com.abdullah.task.service.serviceImpl;

import com.abdullah.task.dto.RequestMetaDTO;
import com.abdullah.task.dto.TaskRegisterRequestDTO;
import com.abdullah.task.dto.TaskUpdateRequestDTO;
import com.abdullah.task.model.Task;
import com.abdullah.task.repository.TaskRepository;
import com.abdullah.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    RequestMetaDTO requestMetaDTO;

    @Override
    public ResponseEntity<?> saveTask(TaskRegisterRequestDTO taskRegisterRequestDTO) {
        if (taskRegisterRequestDTO.getTitle().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter Title");
        } else {
            Task task = new Task();
            task.setTitle(taskRegisterRequestDTO.getTitle());
            task.setComments(taskRegisterRequestDTO.getComments());
            task.setDueDate(taskRegisterRequestDTO.getDueDate());
            task.setCreatedBy(requestMetaDTO.getId());
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task Registered");
        }
    }

    @Override
    public ResponseEntity<?> loadTasks() {
        List<Task> tasks = taskRepository.findAllByActive(true);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @Override
    public ResponseEntity<?> updateTask(Long id, TaskUpdateRequestDTO taskUpdateRequestDTO) {
        if (taskUpdateRequestDTO.getTitle().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter Title");
        } else {
            Optional<Task> optionalTask = taskRepository.findById(id);
            if (optionalTask.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Task");
            }
            Task task = optionalTask.get();
            task.setTitle(taskUpdateRequestDTO.getTitle());
            task.setComments(taskUpdateRequestDTO.getComments());
            task.setDueDate(taskUpdateRequestDTO.getDueDate());
            task.setStatus(taskUpdateRequestDTO.getStatus());
            task.setPriority(taskUpdateRequestDTO.getPriority());
            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.OK).body("Task Updated");
        }
    }

    @Override
    public ResponseEntity<?> deleteTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Task");
        }
        Task task = optionalTask.get();
        task.setActive(false);
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body("Task Deleted");
    }
}
