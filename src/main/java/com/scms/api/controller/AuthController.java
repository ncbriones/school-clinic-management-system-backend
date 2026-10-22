package com.scms.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Login
    @PostMapping("/login")
    public String login() {
        return "Logged in!";
    }

    // Register
    @PostMapping("/register")
    public String register() {
        return "Registered!";
    }
}
