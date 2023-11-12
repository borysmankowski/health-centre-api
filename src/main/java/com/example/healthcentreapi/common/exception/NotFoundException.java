package com.example.healthcentreapi.common.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundException extends EntityNotFoundException {

    public NotFoundException(String message) {
        super(message);
    }
}
