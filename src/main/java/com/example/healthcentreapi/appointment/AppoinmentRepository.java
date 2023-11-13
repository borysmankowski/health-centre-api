package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppoinmentRepository extends JpaRepository<Appointment,Long> {

    boolean existsByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime from, LocalDateTime to);

    List<Appointment> findByDateTimeAfterAndPatient_Id(LocalDateTime dateTime, Long patient_id);
}
