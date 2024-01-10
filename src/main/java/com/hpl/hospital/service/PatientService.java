package com.hpl.hospital.service;

import java.util.List;
import java.util.Optional;

import com.hpl.hospital.domain.Patient;

public interface PatientService {
    
    List<Patient> getPatients();

    Optional<Patient> findPatientByNik(final String nik);

    void save(Patient patient);

    void delete(final String nik);

    void update(final Patient patient);
}

