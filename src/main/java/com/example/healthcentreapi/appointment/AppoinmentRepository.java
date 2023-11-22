package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.doctor.model.Doctor;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;

public interface AppoinmentRepository extends JpaRepository<Appointment,Long> {

    boolean existsByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime from, LocalDateTime to);

    Page<AppointmentDto> findByDateTimeIsBefore(LocalDateTime dateTime, Pageable pageable);


}
