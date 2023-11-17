package com.example.healthcentreapi.patient.model;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.common.Speciality;
import com.example.healthcentreapi.doctor.model.Doctor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @ManyToOne
    private Doctor doctor;

//    @OneToMany(mappedBy = "patient")
//    private List<Appointment> appointments;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    //    public Patient(Long id, String name, String surname, String email, Set<Appointment> appointmentList) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.appointmentList = appointmentList;
//    }
}
