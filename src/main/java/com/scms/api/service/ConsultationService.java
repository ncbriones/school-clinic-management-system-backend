package com.scms.api.service;

import com.scms.api.dto.request.CreateConsultationRequest;
import com.scms.api.dto.response.CreateConsultationResponse;
import com.scms.api.entity.Consultation;
import com.scms.api.entity.Illness;
import com.scms.api.entity.Medicine;
import com.scms.api.entity.Patient;
import com.scms.api.exception.EntityNotFoundException;
import com.scms.api.repository.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final IllnessService illnessService;
    private final MedicineService medicineService;
    private final PatientService patientService;

    public ConsultationService(
            ConsultationRepository consultationRepository,
            IllnessService illnessService,
            MedicineService medicineService,
            PatientService patientService
    ) {
        this.consultationRepository = consultationRepository;
        this.illnessService = illnessService;
        this.medicineService = medicineService;
        this.patientService = patientService;
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public CreateConsultationResponse createConsultation(CreateConsultationRequest request) {
        Illness illness = illnessService.getIllnessById(request.illnessId());
        Medicine medicine = medicineService.getMedicineById(request.medicineId());
        Patient patient = patientService.getPatientById(request.patientId());

        Medicine updatedMedicineStock = medicineService.deductStock(medicine.getId(), request.medicineQuantityGiven());

        Consultation consultation = new Consultation();
        consultation.setReasonOfVisit(request.reasonOfVisit());
        consultation.setMedicineQuantityGiven(request.medicineQuantityGiven());
        consultation.setPatient(patient);
        consultation.setIllness(illness);
        consultation.setMedicineGiven(updatedMedicineStock);

        Consultation savedConsultation = consultationRepository.save(consultation);

        return new CreateConsultationResponse(
                savedConsultation.getId(),
                savedConsultation.getReasonOfVisit(),
                savedConsultation.getMedicineQuantityGiven(),
                savedConsultation.getPatient(),
                savedConsultation.getIllness(),
                savedConsultation.getMedicineGiven(),
                savedConsultation.getVisitedAt()
        );
    }
}