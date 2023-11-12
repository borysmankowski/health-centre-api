package com.example.healthcentreapi.appointment.mapper;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import org.springframework.stereotype.Component;

@Component

public class AppointmentMapper {

    public AppointmentDto toDto(Appointment appointment, long patientId, long doctorId) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .patientId(patientId)
                .doctorId(doctorId)
                .dateTime(appointment.getDateTime())
                .reason(appointment.getReason())
                .build();
    }

    public Appointment fromDto(CreateAppoimentCommand createAppoimentCommand) {
        return Appointment.builder()
                .dateTime(createAppoimentCommand.getDateTime())
                .reason(createAppoimentCommand.getReason())
                .build();
    }
}
