package com.example.healthcentreapi.doctor;

import com.example.healthcentreapi.doctor.mapper.DoctorMapper;
import com.example.healthcentreapi.doctor.model.CreateDoctorCommand;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.doctor.model.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;

    public DoctorDto saveDoctor(CreateDoctorCommand createDoctorCommand) {

        Doctor doctor = doctorMapper.fromDto(createDoctorCommand);

        doctorRepository.save(doctor);

        return doctorMapper.toDto(doctor);
    }
}
