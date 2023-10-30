package com.abdullah.task.dto;

import com.abdullah.task.model.Priority;
import com.abdullah.task.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskUpdateRequestDTO {
    private String title;
    private String comments;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;
}
