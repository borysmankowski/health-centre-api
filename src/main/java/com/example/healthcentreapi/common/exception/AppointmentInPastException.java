package com.example.healthcentreapi.common.exception;

public class AppointmentInPastException extends RuntimeException {

    public AppointmentInPastException(String message){
        super(message);
    }
}
