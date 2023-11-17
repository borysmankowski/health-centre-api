package com.example.healthcentreapi.doctor;

import com.example.healthcentreapi.doctor.model.CreateDoctorCommand;
import com.example.healthcentreapi.doctor.model.DoctorDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto createDoctor(@RequestBody @Valid CreateDoctorCommand command){
        return doctorService.saveDoctor(command);
    }
}
