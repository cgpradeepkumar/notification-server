package com.infosat.notification.server.component;

import com.infosat.notification.server.mybatis.model.Customer;
import com.infosat.notification.server.mybatis.model.EmailOut;
import com.infosat.notification.server.mybatis.model.InsuranceDetail;
import com.infosat.notification.server.mybatis.model.ReminderMessage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author pradeepcg
 * @created 14/11/2020 - 19:30
 */

@Component
public class NotificationBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationBuilder.class);

    @Autowired
    private Configuration configuration;

    //    @Autowired
//    private FreeMarkerConfigurer freeMarkerConfigurer;

    public EmailOut buildEmailMessage(Customer customer, InsuranceDetail insuranceDetail) {
        LOGGER.info("Building Email for customer [{}]", customer.getCustomerId());
        EmailOut emailOut = createEmailOut(customer, insuranceDetail);
        try {
//            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("sample.ftl");
            Template template = configuration.getTemplate("mail/sample.ftl");
            Map<String, Object> data = new HashMap<>();
//            data.put("recipientName", customer.getCreatedBy());
            String customerName = customer.getFirstName() + " " + customer.getLastName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data.put("expiryDate", dateFormat.format(insuranceDetail.getExpiryDate()));
            data.put("customerName", customerName);
            data.put("policyNumber", insuranceDetail.getPolicyNumber());
            data.put("insuranceType", insuranceDetail.getInsuranceType());
            data.put("insuranceProvider", insuranceDetail.getInsuranceProvider());
            data.put("senderName", customer.getCreatedBy());
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
            emailOut.setEmailBody(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return emailOut;
    }

    private EmailOut createEmailOut(Customer customer, InsuranceDetail insuranceDetail) {
        EmailOut emailOut = new EmailOut();
        emailOut.setFromId("conferencepad@gmail.com");
        emailOut.setEmailSubject("Reminder! Policy Expiry");
        emailOut.setToId(customer.getEmailId());
        emailOut.setStatus("P");
        emailOut.setEventTime(insuranceDetail.getExpiryDate());
        emailOut.setCreatedBy(customer.getCreatedBy());
        return emailOut;
    }

    public ReminderMessage buildSms(Customer customer, InsuranceDetail insuranceDetail) {
        LOGGER.info("Building SMS for customer [{}]", customer.getCustomerId());
        ReminderMessage reminderMessage = createReminderMessage(customer);
        try {
            Template template = configuration.getTemplate("sms/sample.ftl");
            Map<String, Object> data = new HashMap<>();
            String customerName = customer.getFirstName() + " " + customer.getLastName();
            data.put("customerName", customerName);
            data.put("policyNumber", insuranceDetail.getPolicyNumber());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data.put("expiryDate", dateFormat.format(insuranceDetail.getExpiryDate()));
            data.put("link", "http://hostname/policy/" + insuranceDetail.getPolicyNumber() + "/renew");
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);

            reminderMessage.setMessage(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return reminderMessage;
    }

    private ReminderMessage createReminderMessage(Customer customer) {
        ReminderMessage reminderMessage = new ReminderMessage();
        reminderMessage.setRecipient(customer.getPrimaryPhone());
        return reminderMessage;
    }
}
