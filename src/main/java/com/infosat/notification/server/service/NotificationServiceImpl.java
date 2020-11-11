package com.infosat.notification.server.service;

import com.infosat.notification.server.mybatis.model.EmailOut;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 13:11
 */

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

//    @Autowired
//    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private Configuration configuration;

    @Autowired
    private EmailService emailService;

    @Override
    public EmailOut sendEmail(EmailOut emailOut) {
        LOGGER.info("Notification received for mailId = [{}]", emailOut.getToId());
        try {
//            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("sample.ftl");
            Template template = configuration.getTemplate("sample.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("recipientName", emailOut.getToId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data.put("nextService", dateFormat.format(emailOut.getEventTime()));
            data.put("senderName", "Conference");
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
            String subject = "Vehicle service reminder!";
            emailService.sendMimeMessage(emailOut.getToId(), subject, body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        LOGGER.info("Notification processed for mailId = [{}]", emailOut.getToId());
        return emailOut;
    }

    @Override
    public void sendSms() {

    }
}
