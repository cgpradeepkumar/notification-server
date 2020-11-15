package com.infosat.notification.server.service;

import com.infosat.notification.server.mybatis.model.EmailOut;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 13:11
 */

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private Configuration configuration;

    @Autowired
    private EmailService emailService;

    @Override
    public EmailOut sendEmail(EmailOut emailOut) {
        LOGGER.info("Notification received for mailId = [{}]", emailOut.getToId());
        emailService.sendMimeMessage(emailOut.getToId(), emailOut.getEmailSubject(), emailOut.getEmailBody());
        LOGGER.info("Notification processed for mailId = [{}]", emailOut.getToId());
        return emailOut;
    }

    @Override
    public void sendSms() {

    }
}
