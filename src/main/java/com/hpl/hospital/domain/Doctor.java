package com.hpl.hospital.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class Doctor {
    
    private String code;

    @NotBlank(message = "full name is required")
    @Size(min = 3, max = 50)
    private String fullName;

    private String specialist;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Practice Schedule is required")
    private Date practiceSchedule;
    

    public Doctor() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPracticeSchedule() {
        return practiceSchedule;
    }

    public void setPracticeSchedule(Date practiceSchedule) {
        this.practiceSchedule = practiceSchedule;
    }
}
