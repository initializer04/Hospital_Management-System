package com.deepakcode.hello.controller;

import com.deepakcode.hello.entity.Appointment;
import com.deepakcode.hello.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Book an appointment (Patients use this)
    @PostMapping("/book")
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    // Get all appointments of a specific patient
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    // Get all patients assigned to a specific doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }
}
