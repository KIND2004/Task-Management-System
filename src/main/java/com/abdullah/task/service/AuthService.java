package com.abdullah.task.service;

import com.abdullah.task.dto.LogInRequestDTO;
import com.abdullah.task.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO);

    ResponseEntity<?> login(LogInRequestDTO logInRequestDTO);
}
