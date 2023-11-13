package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppoitmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto createAppointment(@RequestBody @Valid CreateAppoimentCommand createAppoimentCommand) {
        return appointmentService.createAppointment(createAppoimentCommand, createAppoimentCommand.getDoctorId(), createAppoimentCommand.getPatientId());
    }

    @PutMapping("/{appointmentId}")
    public AppointmentDto updateAppointment(@PathVariable long appointmentId,
                                            @RequestParam LocalDateTime newTime) {
        return appointmentService.updateAppointment(appointmentId, newTime);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointmentById(@PathVariable("id")long idToDelete){
        appointmentService.deleteAppointmentById(idToDelete);
    }
}
