package com.deepakcode.hello.repository;

import com.deepakcode.hello.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Find a patient by ID
    Optional<Patient> findById(Long id);
}
