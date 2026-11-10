package com.scms.api.controller;

import com.scms.api.dto.request.CreatePatientRequest;
import com.scms.api.dto.response.CreatePatientResponse;
import com.scms.api.entity.Patient;
import com.scms.api.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('NURSE', 'ADMIN')")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> allPatients = patientService.getAllPatients();

        return ResponseEntity.ok(allPatients);
    }

    @PostMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<CreatePatientResponse> createPatient(@Valid @RequestBody CreatePatientRequest request) {
        CreatePatientResponse response = patientService.createPatient(request);
        System.out.println(request);

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<String> getPatient() {
        return ResponseEntity.status(HttpStatus.OK).body("Hi");
    }
}
