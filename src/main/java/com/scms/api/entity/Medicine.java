package com.scms.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Integer stockAlert;

    @ManyToMany(mappedBy = "recommendedMedicines")
    private List<Illness> treatedIllnesses = new ArrayList<>();

    @CreatedDate
    @Column(name = "added_at", updatable = false)
    private LocalDate addedAt;

    @Column(name = "expires_at", updatable = false)
    private LocalDate expiresAt;

    public void addIllness(Illness illness) {
        if (!this.treatedIllnesses.contains(illness)) {
            this.treatedIllnesses.add(illness);
            illness.getRecommendedMedicines().add(this);
        }
    }
}
