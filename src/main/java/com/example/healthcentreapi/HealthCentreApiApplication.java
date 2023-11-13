package com.example.healthcentreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class HealthCentreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCentreApiApplication.class, args);
    }

}
