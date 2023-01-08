package com.softcode.notificationserver.service.impl;

import com.softcode.notificationserver.model.Employee;
import com.softcode.notificationserver.model.EmployeeDuty;
import com.softcode.notificationserver.service.DutyChangeEventConsumerService;
import com.softcode.notificationserver.service.EmailService;
import com.softcode.notificationserver.service.EmployeeServiceClient;
import com.softcode.rabbitmqmodel.DutyChangeEvent;
import com.softcode.rabbitmqmodel.DutyChangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "${queue.name}")
public class DutyChangeEventConsumerServiceImpl implements DutyChangeEventConsumerService {


    private final Logger log = LoggerFactory.getLogger(DutyChangeEventConsumerServiceImpl.class);

    private final EmployeeServiceClient employeeServiceClient;
    private final EmailService emailService;

    public DutyChangeEventConsumerServiceImpl(EmployeeServiceClient employeeServiceClient, EmailService emailService) {
        this.employeeServiceClient = employeeServiceClient;
        this.emailService = emailService;
    }


    @Override
    public void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent) {

        log.debug("Duty Change Event Received: EmployeeDuty Id: {} Event Type: {}", dutyChangeEvent.getEmployeeDutyId(), dutyChangeEvent.getDutyChangeType());

        EmployeeDuty dbEmployeeDuty = employeeServiceClient.getEmployeeDuty(dutyChangeEvent.getEmployeeDutyId());
        Employee employee = employeeServiceClient.getEmployeeById(dbEmployeeDuty.getEmployeeId());

        String subject = "";
        String emailLine = "";

        if (dutyChangeEvent.getDutyChangeType() == DutyChangeType.CREATED) {
            subject = "Duty added notification!";
            emailLine = "Please note that the following duty schedule has been assigned to you";
        } else {
            subject = "Duty updated notification!";
            emailLine = "Please note that the following duty schedule of you has been updated";
        }

        emailService.sendDutyChangeEmail(employee.getEmail(), subject, employee.getName(), dbEmployeeDuty.getDutyStart().toString(), dbEmployeeDuty.getDutyEnd().toString(), emailLine);
    }
}
