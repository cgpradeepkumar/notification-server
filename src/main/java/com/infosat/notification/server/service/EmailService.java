package com.infosat.notification.server.service;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 12:00
 */
public interface EmailService {

    public void sendMessage(String to, String subject, String body);

    public void sendMimeMessage(String to, String subject, String body);
}
