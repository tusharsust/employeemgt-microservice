package com.softcode.employeemanagement;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.softcode.employeemanagement", "com.softcode.employeemanagement.api" , "com.softcode.employeemanagement.configuration"})
public class EmployeeServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServerApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}