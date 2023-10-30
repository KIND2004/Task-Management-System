package com.abdullah.task.controller;

import com.abdullah.task.dto.LogInRequestDTO;
import com.abdullah.task.dto.RegisterRequestDTO;
import com.abdullah.task.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequestDTO logInRequestDTO) {
        return authService.login(logInRequestDTO);
    }
}
