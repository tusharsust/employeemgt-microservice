package com.softcode.employeemanagement.service;


import com.softcode.rabbitmqmodel.DutyChangeEvent;

public interface MessageProducerService {
    String produceDutyChangeMessage(DutyChangeEvent dutyChangeEvent);

}
