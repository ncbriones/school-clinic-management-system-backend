package com.scms.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "medicines")
@Getter
@Setter
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
    private List<Sickness> treatedSickness;
}
