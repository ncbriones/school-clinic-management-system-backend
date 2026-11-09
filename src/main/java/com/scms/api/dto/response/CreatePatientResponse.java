package com.scms.api.dto.response;

public record CreatePatientResponse(
        Long id,
        String idNumber,
        String firstName,
        String lastName,
        String gender
) {
}
