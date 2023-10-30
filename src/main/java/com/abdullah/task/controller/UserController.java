package com.abdullah.task.controller;

import com.abdullah.task.dto.UpdateRequestDTO;
import com.abdullah.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/load")
    public ResponseEntity<?> load(){
        return userService.load();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateRequestDTO updateRequestDTO) {
        return userService.update(updateRequestDTO);
    }

    @PutMapping("/delete")
    public ResponseEntity<?> delete() {
        return userService.delete();
    }
}
