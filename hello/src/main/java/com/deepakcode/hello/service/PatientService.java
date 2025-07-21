package com.deepakcode.hello.service;

import com.deepakcode.hello.entity.Patient;
import com.deepakcode.hello.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Add a new patient (Admin can add)
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null); // Return null if patient not found
    }
    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

}

