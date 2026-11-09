package com.scms.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String genericName;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private Integer reorderLevel;

    @ManyToMany(mappedBy = "recommendedMedicines")
    @JsonIgnoreProperties("recommendedMedicines")
    private Set<Illness> treatedIllnesses = new HashSet<>();

    @CreatedDate
    @Column(name = "added_at", updatable = false)
    private LocalDate addedAt;

    @Column(name = "expires_at", updatable = false)
    private LocalDate expiresAt;

    public void addIllness(Illness illness) {

        if (illness == null) return;

        if (!this.treatedIllnesses.contains(illness)) {
            this.treatedIllnesses.add(illness);
            illness.getRecommendedMedicines().add(this);
        }
    }
}
