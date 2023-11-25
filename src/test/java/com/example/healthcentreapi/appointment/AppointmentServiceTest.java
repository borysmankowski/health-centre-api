package com.example.healthcentreapi.appointment;

import com.example.healthcentreapi.appointment.mapper.AppointmentMapper;
import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.appointment.model.CreateAppoimentCommand;
import com.example.healthcentreapi.common.Speciality;
import com.example.healthcentreapi.doctor.DoctorRepository;
import com.example.healthcentreapi.doctor.model.Doctor;
import com.example.healthcentreapi.patient.PatientRepository;
import com.example.healthcentreapi.patient.model.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class AppointmentServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AppoinmentRepository appoinmentRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentService appointmentService;

    @Captor
    private ArgumentCaptor<Appointment> eventArgumentCaptor;

    @Test
    void createAppointment() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        Patient patient = new Patient();
        patient.setId(1L);

        CreateAppoimentCommand createAppoimentCommand = new CreateAppoimentCommand();
        createAppoimentCommand.setDateTime(LocalDateTime.now().plusDays(3));
        createAppoimentCommand.setDoctorId(doctor.getId());
        createAppoimentCommand.setPatientId(patient.getId());
        createAppoimentCommand.setReason("reason");

        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDateTime(LocalDateTime.now().plusDays(3));

        when(doctorRepository.findDoctorByIdForLock(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findPatientByIdForLock(1L)).thenReturn(Optional.of(patient));

        AppointmentDto result = appointmentService.createAppointment(createAppoimentCommand);
        assertNotNull(result);

        verify(appoinmentRepository, times(1)).save(eventArgumentCaptor.capture());
        Appointment saved = eventArgumentCaptor.getValue();

        assertEquals(createAppoimentCommand.getDateTime(), saved.getDateTime());
        assertEquals(doctor, saved.getDoctor());
        assertEquals(patient, saved.getPatient());

    }

    @Test
    void getUpcomingAppointmentsForTomorrow() {
    }

    @Test
    void updateAppointment() {
    }

    @Test
    void deleteAppointmentById() {
    }
}