package com.capgemini.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.capgemini.wsb.fitnesstracker")
public class FitnessTracker {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
    }

}
