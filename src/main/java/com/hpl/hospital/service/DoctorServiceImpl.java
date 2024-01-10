package com.hpl.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.hpl.hospital.domain.Doctor;
import com.hpl.hospital.repository.DoctorEntity;
import com.hpl.hospital.repository.DoctorEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class DoctorServiceImpl implements DoctorService {
    
    private final DoctorEntityRepository doctorEntityRepository;

    private Doctor map(DoctorEntity entity) {
        final Doctor doctor = new Doctor();
        doctor.setCode(entity.getCode());
        doctor.setFullName(entity.getFullName());
        doctor.setSpecialist(entity.getSpecialist());
        doctor.setPracticeSchedule(entity.getPracticeSchedule());
        return doctor;

    }

    private DoctorEntity map(Doctor doctor) {
        final DoctorEntity entity = new DoctorEntity();
        entity.setCode(doctor.getCode());
        entity.setFullName(doctor.getFullName());
        entity.setSpecialist(doctor.getSpecialist());
        entity.setPracticeSchedule(doctor.getPracticeSchedule());
        return entity;

    }

    @Override
    public List<Doctor> getDoctors() {
        final List<DoctorEntity> entities = doctorEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Doctor> findDoctorByCode(String code) {
        Optional<DoctorEntity> optionalDoctorEntity = doctorEntityRepository.findByCode(code);
        if (optionalDoctorEntity.isPresent()) {
            return Optional.of(this.map(optionalDoctorEntity.get()));
        } else {
            return Optional.empty();

        }
    }

    @Override
    public void save(Doctor doctor) {
        doctorEntityRepository.save(this.map(doctor));
    }

    @Override
    public void delete(String code) {
         Optional<DoctorEntity> optionalEntity = doctorEntityRepository.findByCode(code);
        if (optionalEntity.isPresent()) {
            doctorEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("doctor with code {0} not found" + code);
        }
    }

    @Override
    public void update(Doctor doctor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    
}
