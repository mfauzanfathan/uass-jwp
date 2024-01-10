package com.hpl.hospital.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hpl.hospital.domain.Doctor;
import com.hpl.hospital.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DoctorController {
    
    private final DoctorService doctorService;

    @GetMapping("/doctors")
    public String getDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getDoctors());
        return "doctors";
    }

    
    @GetMapping("/signup-doctor")
    public String showSignupForm(Doctor doctor) {
        return "add-doctor";
    }

    @PostMapping("/doctors")
    public String addDoctors(@Valid Doctor doctor, BindingResult bindingResult, Model model) {
        String code = doctor.getCode();

        boolean exists = doctorService.findDoctorByCode(code).isPresent();

        if (exists) {
            throw new IllegalArgumentException("doctor with code:" + code + "is already exist");
        }

        doctorService.save(doctor);

        model.addAttribute("doctors", doctorService.getDoctors());
        return "add-doctor";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/doctors/{code}")
    public ResponseEntity<Doctor> findDoctor(@PathVariable("code") String code) {
        Optional<Doctor> doctorOptional = doctorService.findDoctorByCode(code);
        if (doctorOptional.isPresent()) {
            return new ResponseEntity<>(doctorOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/doctors/{code}")
    public String updateDoctor(@PathVariable("code") String code,
            Doctor doctor,
            BindingResult result, Model model) {

        final Optional<Doctor> optionalDoctor = doctorService.findDoctorByCode(doctor.getCode());
        if (optionalDoctor.isEmpty()) {
            throw new ServiceException("doctor with code:" + code + "is not exists");
        }

        doctorService.update(doctor);

        model.addAttribute("doctors", doctorService.getDoctors());
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{code}")
    public String showUpdateForm(@PathVariable("code") String code, Model model) {
        final Optional<Doctor> optionalDoctor = doctorService.findDoctorByCode(code);
        if (optionalDoctor.isEmpty()) {
            throw new ServiceException("doctor with code:" + code + "is not exists");
        }
        final Doctor doctorToBeUpdated = optionalDoctor.get();

        model.addAttribute("doctor", doctorToBeUpdated);
        return "update-doctor";
    }

    @GetMapping(value = "/doctors/{code}/delete")
    public String deleteDoctor(@PathVariable("code") String code) {
        doctorService.delete(code);
        return "redirect:/doctors";
    }
}
