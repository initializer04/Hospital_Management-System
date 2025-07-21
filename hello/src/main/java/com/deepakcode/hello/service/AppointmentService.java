package com.deepakcode.hello.service;

import com.deepakcode.hello.entity.Appointment;
import com.deepakcode.hello.entity.Patient;
import com.deepakcode.hello.repository.AppointmentRepository;
import com.deepakcode.hello.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;
    // Book an appointment
    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Get all appointments of a patient
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        
        // Get the authenticated username from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find the patient by ID
        Optional<Patient> patient = patientRepository.findById(patientId);

        // Check if the patient exists and the authenticated user is the same as the patient user
        if (patient.isPresent() && patient.get().getUser().getUsername().equals(username)) {
            return appointmentRepository.findByPatientId(patientId);
        }

        // If not authorized, throw an exception or return an empty list
        throw new RuntimeException("Unauthorized access to appointments");
    }

    // Get all patients assigned to a doctor
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findAll().stream()
                .filter(a -> a.getDoctor().getId().equals(doctorId))
                .toList();
    }
}
