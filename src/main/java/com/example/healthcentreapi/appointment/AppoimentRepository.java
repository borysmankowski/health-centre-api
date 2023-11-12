package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppoimentRepository extends JpaRepository<Appointment,Long> {

    boolean existsByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime from, LocalDateTime to);
}
