package com.scms.api.dto.response;

import com.scms.api.entity.Role;

import java.time.LocalDateTime;

public record CreateUserResponse(
    Long id,
    String name,
    String email,
    Role role,
    LocalDateTime createdAt
) {
    public CreateUserResponse(Long id, String name, String email, Role role) {
        this(id, name, email, role, LocalDateTime.now());
    }
}
