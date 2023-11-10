package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoimentRepository extends JpaRepository<Appointment,Long> {
}
