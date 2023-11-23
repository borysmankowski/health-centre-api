package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.common.exception.NotFoundException;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    @Transactional // to nie zadziala bo nie ma proxy, wiec czy powinno byc w appointmentService?
    public PatientDto getPatientById(long patientId) {
        return patientRepository.findPatientByIdForLock(patientId)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));
    }
}
