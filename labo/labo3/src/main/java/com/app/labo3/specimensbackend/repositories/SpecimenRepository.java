package com.app.labo3.specimensbackend.repositories;

import com.app.labo3.specimensbackend.domain.dto.entities.Specimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecimenRepository extends JpaRepository<Specimen, UUID> {
}
