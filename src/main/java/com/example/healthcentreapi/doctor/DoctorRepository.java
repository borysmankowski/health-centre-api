package com.example.healthcentreapi.doctor;

import com.example.healthcentreapi.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
