package com.scms.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LinkMedicineRequest(

        @NotNull(message = "Medicine ID is required.")
        Long medicineId
) {}