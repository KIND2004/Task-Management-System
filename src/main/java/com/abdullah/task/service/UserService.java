package com.abdullah.task.service;

import com.abdullah.task.dto.UpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> load();

    ResponseEntity<?> update(UpdateRequestDTO updateRequestDTO);

    ResponseEntity<?> delete();
}
