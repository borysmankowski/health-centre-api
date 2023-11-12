package com.example.healthcentreapi.patient.model;

import com.example.healthcentreapi.common.Speciality;
import com.example.healthcentreapi.doctor.model.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Email
    private String email;

    @ManyToOne
    private Doctor doctor;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
}
