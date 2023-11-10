package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
