package com.scms.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "illnesses")
@Getter
@Setter
@NoArgsConstructor
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "illness_medicine",
            joinColumns = @JoinColumn(name = "illness_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    @JsonIgnoreProperties("treatedIllnesses")
    private Set<Medicine> recommendedMedicines = new HashSet<>();

    public Illness(String name) {
        this.name = name;
    }

    public void addMedicine(Medicine medicine) {

        if (medicine == null) return;

        if (!this.recommendedMedicines.contains(medicine)) {
            this.recommendedMedicines.add(medicine);
            medicine.getTreatedIllnesses().add(this);
        }
    }
}
