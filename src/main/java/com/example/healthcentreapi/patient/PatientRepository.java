package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.model.Patient;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT v FROM Patient v WHERE v.id = :id")
    Optional<Patient> findPatientByIdForLock(@Param("id") long id);
}
