package com.abdullah.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRegisterRequestDTO {
    private String title;
    private String comments;
    private LocalDate dueDate;
}
