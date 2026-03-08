package com.example.spring_jpa.controllers;

import com.example.spring_jpa.dto.UserDto;
import com.example.spring_jpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.status(200).body(userDtos);
    }


}
