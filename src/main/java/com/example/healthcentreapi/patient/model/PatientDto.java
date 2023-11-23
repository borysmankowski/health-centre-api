package com.example.healthcentreapi.patient.model;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PatientDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
}
