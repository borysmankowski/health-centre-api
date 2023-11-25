package com.example.healthcentreapi.doctor.model;

import com.example.healthcentreapi.common.Speciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DoctorDto {

    private Long id;
    private String name;
    private String surname;
    private Set<Speciality> speciality;
}
