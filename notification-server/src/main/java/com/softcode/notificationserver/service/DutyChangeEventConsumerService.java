package com.softcode.notificationserver.service;


import com.softcode.rabbitmqmodel.DutyChangeEvent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public interface DutyChangeEventConsumerService {

    @RabbitHandler
    void dutyChangeEventHandler(DutyChangeEvent dutyChangeEvent);
}
