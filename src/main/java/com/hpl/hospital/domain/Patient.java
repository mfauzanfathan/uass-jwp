package com.hpl.hospital.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Patient {
    private String nik;

    @NotBlank(message = "full name is required")
    @Size(min = 3, max = 50)
    private String fullName;

    private String disease;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "date of birth is required")
    private Date dateofBirth;
    

    public Patient() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }


    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }
}

