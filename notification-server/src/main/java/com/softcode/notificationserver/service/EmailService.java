package com.softcode.notificationserver.service;

public interface EmailService {
    void sendDutyChangeEmail(String recipient, String subject, String name, String dutyStartTime, String dutyEndTime, String emailLine);
}
