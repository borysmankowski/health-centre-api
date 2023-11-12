package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppoitmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment (@RequestBody @Valid CreateAppoimentCommand createAppoimentCommand){
        return appointmentService.save(createAppoimentCommand, createAppoimentCommand.getDoctorId(), createAppoimentCommand.getPatientId());
    }
}
