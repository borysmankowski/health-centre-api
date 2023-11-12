package com.example.healthcentreapi.appointment.model;

import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime dateTime;

    private String reason;
}
