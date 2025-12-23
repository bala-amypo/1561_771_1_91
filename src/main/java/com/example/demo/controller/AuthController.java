package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final UserService userService;

public AuthController(UserService userService) {
    this.userService = userService;
}

@PostMapping("/register")
public ResponseEntity<AuthResponse> register(@RequestBody User user) {
    User savedUser = userService.register(user);
    AuthResponse response = new AuthResponse(
            null,
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getRole()
    );
    return ResponseEntity.ok(response);
}

@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    AuthResponse response = userService.login(request);
    return ResponseEntity.ok(response);
}


}