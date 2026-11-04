package com.scms.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reasonOfVisit;

    @Column(nullable = false)
    private Integer medicineQuantityGiven;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "sickness_id", nullable = false)
    private Sickness sickness;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicineGiven;

    private LocalDateTime visitedAt;
}
