package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.patient.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Long> {

//    @Query("SELECT new com.example.healthcentreapi.patient.model.Patient(p.id, p.name, p.surname, p.email, p.appointmentList) FROM Patient p")
//    Page<Patient> findAllWithAppointments(Pageable pageable);
}
