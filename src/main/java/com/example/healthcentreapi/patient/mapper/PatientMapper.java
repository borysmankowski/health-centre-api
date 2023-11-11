package com.example.healthcentreapi.patient.mapper;

import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient fromDto(CreatePatientCommand createPatientCommand);

    PatientDto toDto(Patient patient);
}
