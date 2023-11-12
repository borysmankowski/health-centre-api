package com.example.healthcentreapi.doctor.model;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.common.Speciality;
import com.example.healthcentreapi.patient.model.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Set<Speciality> speciality;

    @OneToMany
    private Set<Appointment> appointmentList;

    @OneToMany
    private Set<Patient> patients;
}
