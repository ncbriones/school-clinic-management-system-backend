package com.scms.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateIllnessRequest(

        @NotBlank(message = "Illness name is required")
        String illnessName
) {
}
