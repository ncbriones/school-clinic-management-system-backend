package com.scms.api.controller;

import com.scms.api.dto.request.CreateConsultationRequest;
import com.scms.api.dto.response.CreateConsultationResponse;
import com.scms.api.entity.Consultation;
import com.scms.api.service.ConsultationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('NURSE', 'ADMIN')")
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> allConsultations = consultationService.getAllConsultations();
        return ResponseEntity.ok(allConsultations);
    }

    @PostMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<CreateConsultationResponse> createConsultation(@Valid @RequestBody CreateConsultationRequest request) {
        CreateConsultationResponse response = consultationService.createConsultation(request);
        return ResponseEntity.ok(response);
    }
}
