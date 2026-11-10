package com.scms.api.service;

import com.scms.api.dto.request.CreateMedicineRequest;
import com.scms.api.entity.Medicine;
import com.scms.api.exception.EntityNotFoundException;
import com.scms.api.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();

        return medicines;
    }


    public Medicine getMedicineById(Long medicineId) {
        return medicineRepository.findById(medicineId)
                .orElseThrow(() -> new EntityNotFoundException("Medicine with ID " + medicineId + " not found."));
    }


    public void addMedicine(CreateMedicineRequest request) {
        Medicine medicine = new Medicine();
        medicine.setGenericName(request.genericName());
        medicine.setStockQuantity(request.stockQuantity());
        medicine.setReorderLevel(request.reorderLevel());
        medicine.setExpiresAt(request.expiresAt());

        medicineRepository.save(medicine);
    }

    public Medicine deductStock(Long medicineId, Integer quantityToDeduct) {
        Medicine medicine = getMedicineById(medicineId);

        if (medicine.getStockQuantity() < quantityToDeduct) {
            throw new RuntimeException("Insufficient stock levels for: " + medicine.getGenericName() + " cannot deduct.");
        }

        int updatedStock = medicine.getStockQuantity() - quantityToDeduct;
        medicine.setStockQuantity(updatedStock);

        return medicineRepository.save(medicine);
    }
}
