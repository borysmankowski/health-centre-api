package com.example.healthcentreapi.appointment.model;

import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.model.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    private LocalDateTime dateAndTimeOfAppointment;

    private String reason;
}
