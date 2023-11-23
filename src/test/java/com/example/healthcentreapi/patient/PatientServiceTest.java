package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.mapper.PatientMapper;
import com.example.healthcentreapi.patient.model.CreatePatientCommand;
import com.example.healthcentreapi.patient.model.Patient;
import com.example.healthcentreapi.patient.model.PatientDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private Patient patient;

    private Doctor doctor;

    private PatientDto patientDto;

    private CreatePatientCommand createPatientCommand;


    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;


    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    public void savePatient_WhenValidCommand_ThenReturnDto() {
        // Arrange
        CreatePatientCommand createPatientCommand = new CreatePatientCommand();
        createPatientCommand.setName("John");
        createPatientCommand.setSurname("Doe");
        createPatientCommand.setEmail("john.doe@example.com");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");

        PatientDto patientDto = new PatientDto();
        patientDto.setId(1L);
        patientDto.setName("John");
        patientDto.setSurname("Doe");
        patientDto.setEmail("john.doe@example.com");

        when(patientMapper.fromDto(createPatientCommand)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDto(patient)).thenReturn(patientDto);

        // Act
        PatientDto savedPatientDto = patientService.savePatient(createPatientCommand);

        // Assert
        assertEquals(patientDto.getId(), savedPatientDto.getId());
        assertEquals(patientDto.getName(), savedPatientDto.getName());
        assertEquals(patientDto.getSurname(), savedPatientDto.getSurname());
        assertEquals(patientDto.getEmail(), savedPatientDto.getEmail());
    }


    @Test
    void getPatientById() {
    }
}