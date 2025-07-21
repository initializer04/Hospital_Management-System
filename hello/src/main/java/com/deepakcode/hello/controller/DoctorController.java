package com.deepakcode.hello.controller;

import com.deepakcode.hello.entity.Doctor;
import com.deepakcode.hello.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Add a doctor (Only admin should access this)
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // Get list of all doctors (Accessible to all users)
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
