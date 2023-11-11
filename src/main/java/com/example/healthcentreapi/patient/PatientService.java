package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.doctor.model.DoctorDto;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;
    public PatientDto savePatient(CreatePatientCommand createPatientCommand) {

        Patient patient = patientMapper.fromDto(createPatientCommand);

        patientRepository.save(patient);

        return patientMapper.toDto(patient);
    }
}
