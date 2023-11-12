package com.example.healthcentreapi.appointment.mapper;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment fromDto(CreateAppoimentCommand createAppoimentCommand);

    AppointmentDto toDto(Appointment appointment);
}
