package com.scms.api.controller;

import com.scms.api.dto.request.CreateMedicineRequest;
import com.scms.api.dto.response.CreateMedicineResponse;
import com.scms.api.dto.response.GetMedicinesResponse;
import com.scms.api.entity.Medicine;
import com.scms.api.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('NURSE', 'ADMIN')")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> allMedicines = medicineService.getAllMedicines();

        return ResponseEntity.status(HttpStatus.OK).body(allMedicines);
    }

    @PostMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<CreateMedicineResponse> createMedicine(
            @Valid @RequestBody CreateMedicineRequest request
    ) {
        CreateMedicineResponse response = medicineService.addMedicine(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
