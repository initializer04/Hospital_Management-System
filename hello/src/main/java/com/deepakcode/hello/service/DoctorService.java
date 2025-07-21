package com.deepakcode.hello.service;

import com.deepakcode.hello.entity.Doctor;
import com.deepakcode.hello.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Add a new doctor (Only admin can do this)
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
