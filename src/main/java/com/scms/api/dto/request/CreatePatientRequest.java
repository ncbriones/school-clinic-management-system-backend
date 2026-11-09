package com.scms.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreatePatientRequest(

    @NotBlank(message = "ID Number is required")
    String idNumber,

    @NotBlank(message = "First name is required")
    String firstName,

    @NotBlank(message = "Last name is required")
    String lastName,

    @NotBlank(message = "Gender is required")
    String gender
) {}