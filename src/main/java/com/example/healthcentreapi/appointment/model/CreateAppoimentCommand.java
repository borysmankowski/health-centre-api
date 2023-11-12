package com.example.healthcentreapi.appointment.model;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class CreateAppoimentCommand {

    @Positive(message = "Doctor required")
    private long doctorId;

    @Positive(message = "Patient required")
    private long patientId;

    @NotNull(message = "date required")
    @Future(message = "date has to be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Reason of the appointment cannot be empty")
    private String reason;
}
