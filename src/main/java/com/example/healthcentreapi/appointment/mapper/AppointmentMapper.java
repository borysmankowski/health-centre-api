package com.example.healthcentreapi.appointment.mapper;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment fromDto(AppointmentDto appointmentDto);

    AppointmentDto toDto(Appointment appointment);
}
