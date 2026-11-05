package com.scms.api.controller;

import com.scms.api.dto.request.LoginRequest;
import com.scms.api.dto.response.LoginResponse;
import com.scms.api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/logout")
    public String logout() {
        return "Registered!";
    }
}
