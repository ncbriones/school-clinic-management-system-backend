package com.scms.api.controller;

import com.scms.api.dto.request.CreateUserRequest;
import com.scms.api.dto.response.CreateUserResponse;
import com.scms.api.dto.response.GetUsersResponse;
import com.scms.api.entity.Role;
import com.scms.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetUsersResponse>> getUsers(@RequestParam(required = false) Role role) {
        if (role != null) {
            List<GetUsersResponse> response = userService.getUsersByRole(role);
            return ResponseEntity.ok(response);
        }
        List<GetUsersResponse> response = userService.getUsers();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }

}