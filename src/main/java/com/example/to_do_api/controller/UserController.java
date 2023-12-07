package com.example.to_do_api.controller;

import com.example.to_do_api.dto.RegisterDto;
import com.example.to_do_api.entity.User;
import com.example.to_do_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/registration")
    public ResponseEntity<Object> saveUSer(@RequestBody RegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User result = userService.addUser(new User(registerDto.getMail(), registerDto.getPassword(), registerDto.getRole()));
        if (result.getId() > 0) {
            return ResponseEntity.ok("User Was Saved");
        }
        return ResponseEntity.status(404).body("Error, User Not Saved");
    }

}
