package com.abdullah.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMetaDTO {
    private Long id;
    private String fullName;
    private String email;
}
