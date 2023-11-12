package com.example.healthcentreapi.appointment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AppointmentDto {

    private long id;
    private long patientId;
    private long doctorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime dateTime;

    private String reason;
}
