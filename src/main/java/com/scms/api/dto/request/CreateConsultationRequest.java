package com.scms.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateConsultationRequest(
        @NotNull(message = "Patient id is required")
        Long patientId,

        @NotNull(message = "Illness id is required")
        Long illnessId,

        @NotNull(message = "Medicine id is required")
        Long medicineId,

        @NotBlank(message = "Reason of visit is required")
        String reasonOfVisit,

        @NotNull(message = "Medicine quantity given is required")
        Integer medicineQuantityGiven
) {
}
