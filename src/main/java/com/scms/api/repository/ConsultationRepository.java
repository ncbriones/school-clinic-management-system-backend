package com.scms.api.repository;

import com.scms.api.entity.Consultation;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @EntityGraph(attributePaths = {"patient", "illness", "medicineGiven"})
    List<Consultation> findAll();
}