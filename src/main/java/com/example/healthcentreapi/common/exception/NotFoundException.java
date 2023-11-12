package com.example.healthcentreapi.common.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotFoundException extends EntityNotFoundException {

    private String type;
    private long id;
}
