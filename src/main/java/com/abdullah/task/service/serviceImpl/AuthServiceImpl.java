package com.abdullah.task.service.serviceImpl;

import com.abdullah.task.dto.LogInRequestDTO;
import com.abdullah.task.dto.RegisterRequestDTO;
import com.abdullah.task.model.User;
import com.abdullah.task.repository.UserRepository;
import com.abdullah.task.service.AuthService;
import com.abdullah.task.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getFullName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Full Name Not Found");
        } else if (registerRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Not found");
        } else if (registerRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        } else {
            User user = new User();
            user.setFullName(registerRequestDTO.getFullName());
            user.setEmail(registerRequestDTO.getEmail().toLowerCase());
            user.setPassword(registerRequestDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Registered");
        }
    }

    @Override
    public ResponseEntity<?> login(LogInRequestDTO logInRequestDTO) {
        if (logInRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Not found");
        } else if (logInRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else {
            Optional<User> optionalUser = userRepository.findByEmailIgnoreCaseAndPassword(logInRequestDTO.getEmail(), logInRequestDTO.getPassword());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
            }
            User user = optionalUser.get();
            if (user.isActive()) {
                String accessToken = jwtUtil.generateAccessToken(user);
                Map<String, String> data = new HashMap<>();
                data.put("accessToken", accessToken);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Your Account is Deleted Please Contact Admin");
            }
        }
    }
}
