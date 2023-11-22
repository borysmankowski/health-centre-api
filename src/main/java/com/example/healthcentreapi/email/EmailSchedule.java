package com.example.healthcentreapi.email;

import com.example.healthcentreapi.appointment.AppointmentService;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.doctor.DoctorService;
import com.example.healthcentreapi.patient.PatientService;
import com.example.healthcentreapi.patient.model.PatientDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EmailSchedule {

    private EmailService emailService;

    private PatientService patientService;

    private AppointmentService appointmentService;

    @Scheduled(cron = "${scheduled.cron-expression}")
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
}
