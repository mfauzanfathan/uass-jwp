package com.hpl.hospital.repository;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class PatientEntity {
     @Id  
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nik", nullable = false)
    private String nik;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "disease")
    private String disease;

    @Column(name = "date_of_birth")
    private Date dateofBirth;

}
