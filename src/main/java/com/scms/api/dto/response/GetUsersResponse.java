package com.scms.api.dto.response;

import com.scms.api.entity.Role;

public record GetUsersResponse(
        Long id,
        String name,
        String email,
        Role role
) {}