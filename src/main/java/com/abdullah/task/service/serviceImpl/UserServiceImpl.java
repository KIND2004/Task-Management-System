package com.abdullah.task.service.serviceImpl;

import com.abdullah.task.dto.RequestMetaDTO;
import com.abdullah.task.dto.UpdateRequestDTO;
import com.abdullah.task.model.User;
import com.abdullah.task.repository.UserRepository;
import com.abdullah.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RequestMetaDTO requestMetaDTO;

    @Override
    public ResponseEntity<?> load() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @Override
    public ResponseEntity<?> update(UpdateRequestDTO updateRequestDTO) {
        if (updateRequestDTO.getFullName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Full Name Not Found");
        } else if (updateRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password Not Found");
        } else {
            Optional<User> optionalUser = userRepository.findById(requestMetaDTO.getId());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
            }
            User user = optionalUser.get();
            user.setFullName(updateRequestDTO.getFullName());
            user.setPassword(updateRequestDTO.getPassword());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("User Updated");
        }
    }

    @Override
    public ResponseEntity<?> delete() {
        Optional<User> optionalUser = userRepository.findById(requestMetaDTO.getId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid User");
        }
        User user = optionalUser.get();
        user.setActive(false);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
    }
}
