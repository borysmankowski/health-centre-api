package com.example.healthcentreapi.doctor.model;

import com.example.healthcentreapi.common.Speciality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CreateDoctorCommand {

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{1,19}", message = "first name has to match the pattern {regexp}")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Pattern(regexp = "[A-Z][a-z]{1,19}", message = "first name has to match the pattern {regexp}")
    private String surname;

    @NotEmpty(message = "Speciality required")
    private Set<Speciality> speciality;
}
