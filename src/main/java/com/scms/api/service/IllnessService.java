package com.scms.api.service;

import com.scms.api.dto.request.CreateIllnessRequest;
import com.scms.api.entity.Illness;
import com.scms.api.entity.Medicine;
import com.scms.api.exception.EntityNotFoundException;
import com.scms.api.repository.IllnessRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IllnessService {

    private final IllnessRepository illnessRepository;
    private final MedicineService medicineService;

    public IllnessService(IllnessRepository illnessRepository, MedicineService medicineService) {
        this.illnessRepository = illnessRepository;
        this.medicineService = medicineService;
    }

    public List<Illness> getAllIllness() {
        List<Illness> illnesses = illnessRepository.findAll();

        return illnesses;
    }

    public Illness getIllnessById(Long illnessId) {
        return illnessRepository.findById(illnessId)
                .orElseThrow(() -> new EntityNotFoundException("Illness with ID " + illnessId + " not found."));
    }

    public Illness createIllness(CreateIllnessRequest request) {
        if (illnessRepository.existsByName(request.illnessName())) {
            throw new IllegalArgumentException("Illness already exists.");
        }
        Illness illness = new Illness(request.illnessName());

        Illness savedIllness = illnessRepository.save(illness);
        return savedIllness;
    }

    public void linkMedicine(Long illnessId, Long medicineId) {
        Illness illness = illnessRepository.findById(illnessId)
                .orElseThrow(() -> new EntityNotFoundException("Ilness with ID " + illnessId + " not found."));

        Medicine medicine = medicineService.getMedicineById(medicineId);

        illness.addMedicine(medicine);
        illnessRepository.save(illness);
    }

    public boolean illnessExists(String name) {
        return illnessRepository.existsByName(name);
    }
}
