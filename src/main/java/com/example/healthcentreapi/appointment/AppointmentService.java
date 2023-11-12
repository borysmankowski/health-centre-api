package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.mapper.AppointmentMapper;
import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import com.example.healthcentreapi.common.exception.AppointmentConflictException;
import com.example.healthcentreapi.common.exception.NotFoundException;
import com.example.healthcentreapi.doctor.DoctorRepository;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.PatientRepository;
import com.example.healthcentreapi.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppoimentRepository appoimentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentDto createAppointment(CreateAppoimentCommand createAppoimentCommand, long doctorId, long patientId) {

        LocalDateTime newTime = createAppoimentCommand.getDateTime();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Doctor id: {0} has not been found",doctorId)));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient id: {0} has not been found",patientId)));

        if (appoimentRepository.existsByDoctorAndDateTimeBetween(doctor, newTime.minusMinutes(59),
                newTime.plusMinutes(59))) {
            throw new AppointmentConflictException("There is another appointment happening at that time");
        }

        Appointment appointment = appointmentMapper.fromDto(createAppoimentCommand);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appoimentRepository.save(appointment);
        return appointmentMapper.toDto(appointment,appointment.getPatient().getId(),appointment.getDoctor().getId());

    }
}
