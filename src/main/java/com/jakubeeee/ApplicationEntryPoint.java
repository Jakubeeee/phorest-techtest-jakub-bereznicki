package com.jakubeeee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class ApplicationEntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEntryPoint.class, args);
    }

}