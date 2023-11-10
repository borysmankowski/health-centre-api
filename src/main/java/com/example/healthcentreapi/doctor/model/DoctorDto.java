package com.example.healthcentreapi.doctor.model;

import com.example.healthcentreapi.common.Speciality;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String surname;
    private Set<Speciality> speciality;
}
