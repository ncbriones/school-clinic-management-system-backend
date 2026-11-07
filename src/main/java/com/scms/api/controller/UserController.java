package com.scms.api.controller;

import com.scms.api.dto.request.CreateNurseRequest;
import com.scms.api.dto.response.CreateNurseResponse;
import com.scms.api.dto.response.GetNursesResponse;
import com.scms.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreateNurseResponse> createNurse(@Valid @RequestBody CreateNurseRequest request) {
        CreateNurseResponse response = userService.createNewNurse(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GetNursesResponse>> getAllNurses() {
        List<GetNursesResponse> allNurse = userService.getAllNurse();

        return ResponseEntity.status(HttpStatus.OK).body(allNurse);
    }

    @DeleteMapping("/{nurseId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteNurseById(@PathVariable Long nurseId) {
        userService.deleteNurseById(nurseId);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}