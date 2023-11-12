package com.example.healthcentreapi.common.exception;

public class AppointmentConflictException extends RuntimeException{

    public AppointmentConflictException(String message){
        super(message);
    }
}
