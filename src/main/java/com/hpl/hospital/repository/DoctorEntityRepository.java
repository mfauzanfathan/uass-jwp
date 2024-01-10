package com.hpl.hospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorEntityRepository extends JpaRepository<DoctorEntity, Long> {
    
    Optional<DoctorEntity> findByCode(final String code);

}
