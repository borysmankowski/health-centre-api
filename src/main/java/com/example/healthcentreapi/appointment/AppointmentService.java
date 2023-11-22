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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppoinmentRepository appoinmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentDto createAppointment(CreateAppoimentCommand createAppoimentCommand) {

        LocalDateTime newTime = createAppoimentCommand.getDateTime();

        Doctor doctor = doctorRepository.findDoctorByIdForLock(createAppoimentCommand.getDoctorId())
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Doctor id: {0} has not been found",createAppoimentCommand.getDoctorId())));

        Patient patient = patientRepository.findPatientByIdForLock(createAppoimentCommand.getPatientId())
                .orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient id: {0} has not been found",createAppoimentCommand.getPatientId())));

        if (appoinmentRepository.existsByDoctorAndDateTimeBetween(doctor, newTime.minusMinutes(59),
                newTime.plusMinutes(59))) {
            throw new AppointmentConflictException("There is another appointment happening at that time");
        }

        Appointment appointment = appointmentMapper.fromDto(createAppoimentCommand);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        return appointmentMapper.toDto(appoinmentRepository.save(appointment));

    }

    public List<AppointmentDto> getUpcomingAppointmentsForTomorrow(LocalDateTime tomorrow, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AppointmentDto> appointmentDtoPage = appoinmentRepository
                .findByDateTimeIsBefore(tomorrow, pageable);
        return appointmentDtoPage.getContent();
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

    @Transactional
    public void deleteAppointmentById(long idToDelete) {

        Appointment appointment = appoinmentRepository.findById(idToDelete)
                .orElseThrow(() -> new NotFoundException("Appointment id {0} was not found!" + idToDelete));

        if(appointment.getDateTime().isBefore(LocalDateTime.now())){
            throw new AppointmentInPastException("Appointment is taking place during another appointment {0} " + appointment.getDateTime());
        }
        appoinmentRepository.deleteById(idToDelete);
    }

//    public Page<AppointmentDto> findAllAppointments(Pageable pageable){
//        Page<Appointment> appointments = appoinmentRepository.findAll(pageable);
//        return appointments.map(appointmentMapper::toDto);
//    }

}
