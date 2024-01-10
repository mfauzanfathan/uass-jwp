package com.hpl.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.hpl.hospital.domain.Patient;
import com.hpl.hospital.repository.PatientEntity;
import com.hpl.hospital.repository.PatientEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class PatientServiceImpl implements PatientService {

    private final PatientEntityRepository patientEntityRepository;
    
    private Patient map(PatientEntity entity) {
        final Patient patient = new Patient();
        patient.setNik(entity.getNik());
        patient.setFullName(entity.getFullName());
        patient.setDisease(entity.getDisease());
        patient.setDateofBirth(entity.getDateofBirth());
        return patient;

    }

    private PatientEntity map(Patient patient) {
        final PatientEntity entity = new PatientEntity();
        entity.setNik(patient.getNik());
        entity.setFullName(patient.getFullName());
        entity.setDisease(patient.getDisease());
        entity.setDateofBirth(patient.getDateofBirth());
        return entity;

    }
    @Override
    public List<Patient> getPatients() {
        final List<PatientEntity> entities = patientEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Patient> findPatientByNik(String nik) {
        Optional<PatientEntity> optionalPatientEntity = patientEntityRepository.findByNik(nik);
        if (optionalPatientEntity.isPresent()) {
            return Optional.of(this.map(optionalPatientEntity.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Patient patient) {
        patientEntityRepository.save(this.map(patient));
    }

    @Override
    public void delete(String nik) {
        Optional<PatientEntity> optionalEntity = patientEntityRepository.findByNik(nik);
        if (optionalEntity.isPresent()) {
            patientEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("patient with nik {0} not found" + nik);
        }
    }

    @Override
    public void update(Patient patient) {
        Optional<PatientEntity> optionalEntity = patientEntityRepository.findByNik(patient.getNik());
    if (optionalEntity.isPresent()) {
        // Memperbarui informasi pasien dengan data yang baru
        PatientEntity existingEntity = optionalEntity.get();
        existingEntity.setFullName(patient.getFullName());
        existingEntity.setDisease(patient.getDisease());
        existingEntity.setDateofBirth(patient.getDateofBirth());

        // Menyimpan pembaruan ke database
        patientEntityRepository.save(existingEntity);
    } else {
        throw new ServiceException("Patient with NIK " + patient.getNik() + " not found");
    }
    
}
}
