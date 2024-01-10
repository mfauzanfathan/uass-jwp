package com.hpl.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    
@Repository
public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {
    
    Optional<PatientEntity> findByNik(final String nik);

}
