package com.example.healthcentreapi.appointment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AppointmentDto {

    private int id;
    private int patientId;
    private int doctorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime dateAndTimeOfAppointment;

    private String reason;
}
