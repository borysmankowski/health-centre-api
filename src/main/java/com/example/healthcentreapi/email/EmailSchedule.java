package com.example.healthcentreapi.email;

import com.example.healthcentreapi.appointment.AppointmentService;
import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.doctor.DoctorService;
import com.example.healthcentreapi.patient.PatientService;
import com.example.healthcentreapi.patient.model.PatientDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EmailSchedule {

    private EmailService emailService;

    private PatientService patientService;

    private DoctorService doctorService;

    private AppointmentService appointmentService;

    @Scheduled(cron = "1 * * * * *")
    public void sendScheduledEmailNotification() {

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        int page = 0;
        int pageSize = 1000;

        List<AppointmentDto> upcomingAppointments;

        do {
            upcomingAppointments = appointmentService.getUpcomingAppointmentsForTomorrow(tomorrow, page, pageSize);

            for (AppointmentDto appointment : upcomingAppointments) {
                PatientDto patientDto = patientService.getPatientById(appointment.getPatientId());
                emailService.sendAppointmentReminderEmail(patientDto, upcomingAppointments);
            }

            page++;
        } while (!upcomingAppointments.isEmpty());
    }


    private boolean isAppointmentTomorrow(AppointmentDto appointment, LocalDateTime tomorrow) {
        LocalDateTime appointmentDateTime = appointment.getDateTime();
        return appointmentDateTime.toLocalDate().isEqual(tomorrow.toLocalDate());
    }
}
