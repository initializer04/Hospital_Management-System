package com.deepakcode.hello.controller;

import com.deepakcode.hello.entity.Patient;
import com.deepakcode.hello.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Add a patient (Only admin should access this)
    @PostMapping("/add")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }
    // Get all patients (Only admin should access this)
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
}
