package com.scms.api.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateMedicineRequest(

        @NotBlank(message = "Medicine generic name is required")
        String genericName,

        @NotNull(message = "Stock quantity is required")
        @Min(value = 0, message = "Stock cannot be negative")
        Integer stockQuantity,

        @NotNull(message = "Reorder level is required for low-stock alert")
        @Min(value = 0, message = "Reorder level cannot be negative")
        Integer reorderLevel,

        @NotNull(message = "Expiration date is required")
        @Future(message = "Expiration date must be a future date")
        LocalDate expiresAt
) {
}
