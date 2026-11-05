package com.scms.api.dto.response;

import com.scms.api.entity.Role;

public record LoginResponse(
        String type,
        String token,
        String name,
        Role role
) {}