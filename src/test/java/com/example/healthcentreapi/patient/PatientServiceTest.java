package com.example.healthcentreapi.patient;

import com.example.healthcentreapi.common.exception.NotFoundException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

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
    public void savePatient_WhenInValidCommandEmail_ThenThrowsDataIntegrityException() {
        // Arrange
        CreatePatientCommand createPatientCommand = new CreatePatientCommand();
        createPatientCommand.setName("John");
        createPatientCommand.setSurname("Doe");
        createPatientCommand.setEmail(String.valueOf(123));

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("John");
        patient.setSurname("Doe");

        when(patientMapper.fromDto(createPatientCommand)).thenReturn(patient);
        when(patientRepository.save(patient)).thenThrow(new DataIntegrityViolationException("Error"));
        // Act
        assertThrows(DataIntegrityViolationException.class, () -> patientService.savePatient(createPatientCommand));

        // Assert
    }

    @Test
    public void savePatient_WhenNameIsNull_ThenThrowsNullPointerException() {
        // Arrange
        CreatePatientCommand createPatientCommand = new CreatePatientCommand();
        createPatientCommand.setName("");
        createPatientCommand.setSurname("Doe");
        createPatientCommand.setEmail("john.doe@example.com");

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");

        when(patientMapper.fromDto(createPatientCommand)).thenReturn(patient);
        when(patientRepository.save(patient)).thenThrow(new NullPointerException("First name cannot be blank"));
        // Act
        assertThrows(NullPointerException.class, () -> patientService.savePatient(createPatientCommand));

        // Assert
    }

    @Test
    public void savePatient_WhenSurnameIsNull_ThenThrowsMethodArgumentNotValidException() {
        // Arrange
        CreatePatientCommand createPatientCommand = new CreatePatientCommand();
        createPatientCommand.setName("John");
        createPatientCommand.setSurname("");
        createPatientCommand.setEmail("john.doe@example.com");

        assertThrows(MethodArgumentNotValidException.class, () -> patientService.savePatient(createPatientCommand));
        verifyNoInteractions(patientRepository);

//        when(patientMapper.fromDto(createPatientCommand)).thenReturn(patient);
//
//        when(patientRepository.save(patient)).thenThrow(new NullPointerException("Surname cannot be blank"));
        // Act

        // Assert
    }

    @Test
    void testGetPatientById_SuccessfullyFound() {

        long id = 1L;

        Patient patient = new Patient();
        patient.setId(id);
        patient.setName("John");
        patient.setSurname("Doe");
        patient.setEmail("john.doe@example.com");

        PatientDto patientDto = new PatientDto();
        patientDto.setId(id);
        patientDto.setName("John");
        patientDto.setSurname("Doe");
        patientDto.setEmail("john.doe@example.com");

        when(patientRepository.findPatientByIdForLock(id)).thenReturn(Optional.of(patient));
        when(patientMapper.toDto(patient)).thenReturn(patientDto);

        PatientDto result = patientService.getPatientById(id);

        assertEquals(patientDto, result);

    }

    @Test
    void testGetPatientById_NotFound_ThrowsResourceNotFoundException() {

        long id = 1L;

        when(patientRepository.findPatientByIdForLock(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> patientService.getPatientById(id));

        assertEquals("Patient not found with id: " + id, exception.getMessage());

    }
}