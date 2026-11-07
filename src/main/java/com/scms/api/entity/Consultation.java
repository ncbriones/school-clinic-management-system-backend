package com.scms.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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
    @JoinColumn(name = "illness_id", nullable = false)
    private Illness illness;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicineGiven;

    @CreatedDate
    @Column(name = "visited_at", updatable = false)
    private LocalDateTime visitedAt;
}
