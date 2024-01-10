package com.hpl.hospital.service;

import java.util.List;
import java.util.Optional;

import com.hpl.hospital.domain.Doctor;

public interface DoctorService {
    
    List<Doctor> getDoctors();

    Optional<Doctor> findDoctorByCode(final String code);

    void save(Doctor doctor);

    void delete(final String code);

    void update(final Doctor doctor);
}
