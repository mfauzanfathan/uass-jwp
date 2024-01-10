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

import com.hpl.hospital.domain.Patient;
import com.hpl.hospital.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    
    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getPatients());
        return "patients";
    }
    
    @GetMapping("/signup-patient")
    public String showSignupForm(Patient patient) {
        return "add-patient";
    }
    

    @PostMapping("/patients")
    public String addPatient(@Valid Patient patient, BindingResult bindingResult, Model model) {
        String nik = patient.getNik();

        boolean exists = patientService.findPatientByNik(nik).isPresent();

        if (exists) {
            throw new IllegalArgumentException("patient with nik:" + nik + "is already exist");
        }

        patientService.save(patient);

        model.addAttribute("patients", patientService.getPatients());
        return "patients";
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @GetMapping(value = "/patients/{nik}")
    public ResponseEntity<Patient> findPatient(@PathVariable("nik") String nik) {
        Optional<Patient> patientOptional = patientService.findPatientByNik(nik);
        if (patientOptional.isPresent()) {
            return new ResponseEntity<>(patientOptional.get(), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/patients/{nik}")
    public String updatePatient(@PathVariable("nik") String nik,
            Patient patient,
            BindingResult result, Model model) {

        final Optional<Patient> optionalPatient = patientService.findPatientByNik(patient.getNik());
        if (optionalPatient.isEmpty()) {
            throw new ServiceException("patient with nik:" + nik + "is not exists");
        }

        patientService.update(patient);

        model.addAttribute("patients", patientService.getPatients());
        return "redirect:/patients";
    }

    @GetMapping("/patients/edit/{nik}")
    public String showUpdateForm(@PathVariable("nik") String nik, Model model) {
        final Optional<Patient> optionalPatient = patientService.findPatientByNik(nik);
        if (optionalPatient.isEmpty()) {
            throw new ServiceException("patient with nik:" + nik + "is not exists");
        }
        final Patient patientToBeUpdated = optionalPatient.get();

        model.addAttribute("patient", patientToBeUpdated);
        return "update-patient";
    }

    @GetMapping(value = "/patients/{nik}/delete")
    public String deletePatient(@PathVariable("nik") String nik) {
        patientService.delete(nik);
        return "redirect:/patients";
    }
}
