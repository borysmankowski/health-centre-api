package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.mapper.AppointmentMapper;
import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import com.example.healthcentreapi.common.exception.*;
import com.example.healthcentreapi.doctor.DoctorRepository;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.PatientRepository;
import com.example.healthcentreapi.patient.model.Patient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppoinmentRepository appoinmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentDto createAppointment(CreateAppoimentCommand createAppoimentCommand, long doctorId, long patientId) {

        LocalDateTime newTime = createAppoimentCommand.getDateTime();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Doctor id: {0} has not been found",doctorId)));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient id: {0} has not been found",patientId)));

        if (appoinmentRepository.existsByDoctorAndDateTimeBetween(doctor, newTime.minusMinutes(59),
                newTime.plusMinutes(59))) {
            throw new AppointmentConflictException("There is another appointment happening at that time");
        }

        Appointment appointment = appointmentMapper.fromDto(createAppoimentCommand);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appoinmentRepository.save(appointment);
        return appointmentMapper.toDto(appointment);

    }

    public List<AppointmentDto> getUpcomingAppointmentsForPatient(Long patientId, LocalDateTime tomorrow) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException("Patient not found with id: " + patientId));

        List<AppointmentDto> upcomingAppointments = appoinmentRepository
                .findByDateTimeAfterAndPatient_Id(tomorrow,patientId);

        return new ArrayList<>(upcomingAppointments);
    }

    @Transactional
    public AppointmentDto updateAppointment(long appointmentId, LocalDateTime newTime) {

        if (newTime.isBefore(LocalDateTime.now())){
            throw new TermNotAvailableException("Appointment cannot be scheduled in the past, you've added" + newTime);
        }

        Appointment appointment = appoinmentRepository.findById(appointmentId)
                        .orElseThrow(() -> new NotFoundException("Appointment id: {0} was not found" + appointmentId));

        if (appointment.getDateTime().isBefore(LocalDateTime.now())){
            throw new AppointmentAlreadyStartedException("The appointment has already started!");
        }

        if(appoinmentRepository.existsByDoctorAndDateTimeBetween(appointment.getDoctor(),newTime.minusMinutes(59),
                newTime.plusMinutes(59))){
            throw new TermNotAvailableException("You cannot plan an appointment in that time, the doctor is busy!");
        }
        appointment.setDateTime(newTime);
        appoinmentRepository.save(appointment);

        return appointmentMapper.toDto(appointment);
    }

    public void deleteAppointmentById(long idToDelete) {

        Appointment appointment = appoinmentRepository.findById(idToDelete)
                .orElseThrow(() -> new NotFoundException("Appointment id {0} was not found!" + idToDelete));

        if(appointment.getDateTime().isBefore(LocalDateTime.now())){
            throw new AppointmentInPastException("Appointment is taking place during another appointment {0} " + appointment.getDateTime());
        }
        appoinmentRepository.deleteById(idToDelete);
    }
}
