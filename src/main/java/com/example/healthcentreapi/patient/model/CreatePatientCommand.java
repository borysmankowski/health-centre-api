package com.example.healthcentreapi.patient.model;

import com.example.healthcentreapi.common.Speciality;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreatePatientCommand {

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{1,19}", message = "first name has to match the pattern {regexp}")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{1,19}", message = "first name has to match the pattern {regexp}")
    private String surname;

    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;

}
