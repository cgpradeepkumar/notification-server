package com.infosat.notification.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 12:16
 */

@Service
public class GmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("classpath:/images/logo.jpg")
    private Resource imageResource;

    @Override
    public void sendMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("conferencepad@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @Override
    public void sendMimeMessage(String to, String subject, String body) {
        LOGGER.info("Sending MIME Message to emailId = [{}]", to);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("conferencepad@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.addInline("logo.jpg", imageResource);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        LOGGER.info("MIME Message sent to emailId = [{}]", to);
    }
}
