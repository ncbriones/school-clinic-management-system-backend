package com.scms.api.repository;

import com.scms.api.entity.Illness;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, Long> {

    boolean existsByName(String name);

    @EntityGraph(attributePaths = {"recommendedMedicines"})
    List<Illness> findAll();
}