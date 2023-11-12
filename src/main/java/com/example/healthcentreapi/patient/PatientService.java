package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.common.exception.InvalidDoctorSpecialityException;
import com.example.healthcentreapi.common.exception.NotFoundException;
import com.example.healthcentreapi.doctor.DoctorRepository;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.doctor.model.DoctorDto;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientDto savePatient(CreatePatientCommand createPatientCommand, long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Doctor id: {0} has not been found",doctorId)));

        if (!doctor.getSpeciality().contains(createPatientCommand.getSpeciality())) {
            throw new InvalidDoctorSpecialityException(MessageFormat
                    .format("Speciality {0} is not being handled by this doctor", createPatientCommand.getSpeciality()));
        }

        Patient patient = patientMapper.fromDto(createPatientCommand);
        patient.setDoctor(doctor);
        patientRepository.save(patient);

        return patientMapper.toDto(patient);
    }
}
