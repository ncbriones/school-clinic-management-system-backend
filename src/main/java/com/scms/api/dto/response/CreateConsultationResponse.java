package com.scms.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scms.api.entity.Illness;
import com.scms.api.entity.Medicine;
import com.scms.api.entity.Patient;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public record CreateConsultationResponse(
        Long id,

        String reasonOfVisit,

        Integer medicineQuantityGiven,

        Patient patient,

        Illness illness,

        Medicine medicineGiven,

        LocalDateTime visitedAt
) {}