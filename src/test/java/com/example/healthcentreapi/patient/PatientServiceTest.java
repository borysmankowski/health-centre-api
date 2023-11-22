package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class PatientServiceTest {

    private Patient patient;

    private CreatePatientCommand createPatientCommand;

    private Doctor doctor;

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("test@gmail.com");
        patient.setVersion(1L);

        createPatientCommand = new CreatePatientCommand();
        createPatientCommand.setName("John");
        createPatientCommand.setSurname("Doe");
        createPatientCommand.setEmail("test@gmail.com");

    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    void savePatient() {

        when(patientMapper.fromDto(createPatientCommand)).thenReturn(patient);
        patientService.savePatient(createPatientCommand);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        verify(patientRepository).save(patient);
    }

    @Test
    void getPatientById() {
    }
}