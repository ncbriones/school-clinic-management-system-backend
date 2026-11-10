package com.scms.api.controller;

import com.scms.api.dto.request.CreateIllnessRequest;
import com.scms.api.dto.request.LinkMedicineRequest;
import com.scms.api.entity.Illness;
import com.scms.api.service.IllnessService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/illnesses")
public class IllnessController {

    private final IllnessService illnessService;

    public IllnessController(IllnessService illnessService) {
        this.illnessService = illnessService;
    }

    @GetMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<List<Illness>> getAllIllness() {
        List<Illness> allIlness = illnessService.getAllIllness();

        return ResponseEntity.ok(allIlness);
    }

    @GetMapping("/{illnessId}")
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Illness> getIllness(@PathVariable Long illnessId) {
        Illness illness = illnessService.getIllnessById(illnessId);

        return ResponseEntity.ok(illness);
    }

    @PostMapping
    @PreAuthorize("hasRole('NURSE')")
    public ResponseEntity<Illness> createIllness(@Valid @RequestBody CreateIllnessRequest request) {
        Illness illness = illnessService.createIllness(request);

        return ResponseEntity.ok(illness);
    }

    @PostMapping("/{illnessId}/medicines")
    public ResponseEntity<String> linkMedicine(@PathVariable Long illnessId, @Valid @RequestBody LinkMedicineRequest request) {
        illnessService.linkMedicine(illnessId, request.medicineId());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> verifyIllnessExistence(@RequestParam String name) {
        boolean exists = illnessService.illnessExists(name);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

}
