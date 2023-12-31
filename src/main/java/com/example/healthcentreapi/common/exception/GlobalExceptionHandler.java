package com.example.healthcentreapi.common.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(AppointmentConflictException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleAppointmentConflictException(AppointmentConflictException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(InvalidDoctorSpecialityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleInvalidSpecialityException(InvalidDoctorSpecialityException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(TermNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleUpdatingAppointmentAndTermNotAvailableException(TermNotAvailableException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(AppointmentAlreadyStartedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleLessonAlreadyStartedException(AppointmentAlreadyStartedException exception) {
        return new ExceptionDto(exception.getMessage());
    }

    @ExceptionHandler(AppointmentInPastException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleLessonInPastException(AppointmentInPastException exception) {
        return new ExceptionDto(exception.getMessage());
    }
}
