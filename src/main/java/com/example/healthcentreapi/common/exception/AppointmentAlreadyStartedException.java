package com.example.healthcentreapi.common.exception;

public class AppointmentAlreadyStartedException extends RuntimeException{

    public AppointmentAlreadyStartedException(String message){
        super(message);
    }
}
