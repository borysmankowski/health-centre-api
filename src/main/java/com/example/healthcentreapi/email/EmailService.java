package com.example.healthcentreapi.email;

import com.example.healthcentreapi.appointment.model.Appointment;
import com.example.healthcentreapi.appointment.model.AppointmentDto;
import com.example.healthcentreapi.patient.model.PatientDto;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendAppointmentReminderEmail(PatientDto patientDto, List<AppointmentDto> appointments) {

        String subject = "Appointment Reminder";
        String body = "Dear " + patientDto.getName() + ",\n\n"
                + "This is a reminder for your upcoming appointment(s):\n";

        for (AppointmentDto appointment : appointments) {
            body += "- " + appointment.getDateTime() + " with Dr. "
                    + appointment.getDoctorId() + "\n";
        }

        body += "\nPlease make sure to attend the appointment on time. "
                + "If you have any questions, feel free to contact us.";

        String finalBody = body;
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(patientDto.getEmail());
            messageHelper.setSubject(subject);
            messageHelper.setText(finalBody, true);
        };

        mailSender.send(messagePreparator);
    }


}
