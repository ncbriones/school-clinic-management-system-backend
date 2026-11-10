package com.scms.api.service;

import com.scms.api.dto.request.CreatePatientRequest;
import com.scms.api.dto.response.CreatePatientResponse;
import com.scms.api.entity.Patient;
import com.scms.api.exception.EntityNotFoundException;
import com.scms.api.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public CreatePatientResponse createPatient(CreatePatientRequest request) {
        return getPatientByIdNumber(request.idNumber())
                .map(existingPatient -> new CreatePatientResponse(
                        existingPatient.getId(),
                        existingPatient.getIdNumber(),
                        existingPatient.getFirstName(),
                        existingPatient.getLastName(),
                        existingPatient.getGender()
                ))
                .orElseGet(() -> addPatient(request));
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + id + " not found."));
    }

    public Optional<Patient> getPatientByIdNumber(String idNumber) {
        if (idNumber == null || idNumber.isEmpty()) {
            return Optional.empty();
        }
        return patientRepository.findByIdNumber(idNumber.trim());
    }

    public CreatePatientResponse addPatient(CreatePatientRequest request) {
        Patient patient = new Patient();

        patient.setIdNumber(request.idNumber().trim());
        patient.setFirstName(request.firstName().trim());
        patient.setLastName(request.lastName().trim());
        patient.setGender(request.gender().trim());

        Patient savedPatient = patientRepository.save(patient);

        return new CreatePatientResponse(
                savedPatient.getId(),
                savedPatient.getIdNumber(),
                savedPatient.getFirstName(),
                savedPatient.getLastName(),
                savedPatient.getGender()
        );
    }
}