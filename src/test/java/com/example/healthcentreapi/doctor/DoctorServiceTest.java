package com.example.healthcentreapi.doctor;

import com.example.healthcentreapi.common.Speciality;
import com.example.healthcentreapi.doctor.mapper.DoctorMapper;
import com.example.healthcentreapi.doctor.model.CreateDoctorCommand;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.doctor.model.DoctorDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;


//    @AfterEach
//    void tearDown() {
//        doctorRepository.deleteAll();
//    }

    @Test
    void saveDoctor_WhenValidCommand_ThenReturnDto() {

        CreateDoctorCommand createDoctorCommand = new CreateDoctorCommand();
        createDoctorCommand.setName("John");
        createDoctorCommand.setSurname("Doe");
        createDoctorCommand.setSpeciality(Set.of(Speciality.DERMATOLOGY));

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("John");
        doctor.setSurname("Doe");
        doctor.setSpeciality(Set.of(Speciality.DERMATOLOGY));

        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(1L);
        doctorDto.setName("John");
        doctorDto.setSurname("Doe");
        doctorDto.setSpeciality(Set.of(Speciality.DERMATOLOGY));

        when(doctorMapper.fromDto(createDoctorCommand)).thenReturn(doctor);
        when(doctorRepository.save(doctor)).thenReturn(doctor);
        when(doctorMapper.toDto(doctor)).thenReturn(doctorDto);

        DoctorDto savedDoctorDto = doctorService.saveDoctor(createDoctorCommand);

        assertEquals(doctorDto.getId(), savedDoctorDto.getId());
        assertEquals(doctorDto.getName(), savedDoctorDto.getName());
        assertEquals(doctorDto.getSurname(), savedDoctorDto.getSurname());
        assertEquals(doctorDto.getSpeciality(), savedDoctorDto.getSpeciality());
    }
}