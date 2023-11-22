package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.common.exception.NotFoundException;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;

    @Transactional
    public PatientDto savePatient(CreatePatientCommand createPatientCommand) {
        Patient patient = patientMapper.fromDto(createPatientCommand);
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public Page<PatientDto> findAllPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(patientMapper::toDto);
    }

    public PatientDto getPatientById(long patientId) {
        return patientRepository.findById(patientId)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));
    }
}
