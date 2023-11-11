package com.example.healthcentreapi.doctor.mapper;

import com.example.healthcentreapi.doctor.model.CreateDoctorCommand;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.doctor.model.DoctorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor fromDto(CreateDoctorCommand createDoctorCommand);

    DoctorDto toDto(Doctor doctor);

}
