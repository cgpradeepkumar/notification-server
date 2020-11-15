package com.infosat.notification.server.component;

import com.infosat.notification.server.mybatis.mapper.CustomerMapper;
import com.infosat.notification.server.mybatis.mapper.EmailOutMapper;
import com.infosat.notification.server.mybatis.mapper.InsuranceDetailMapper;
import com.infosat.notification.server.mybatis.mapper.ReminderMessageMapper;
import com.infosat.notification.server.mybatis.model.Customer;
import com.infosat.notification.server.mybatis.model.EmailOut;
import com.infosat.notification.server.mybatis.model.InsuranceDetail;
import com.infosat.notification.server.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 16:32
 */

@Component
public class NotificationScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationScheduler.class);
    private static final String STATUS_PENDING = "P";
    private static final String STATUS_SUCCESS = "S";

    @Autowired
    private EmailOutMapper emailOutMapper;

    @Autowired
    private InsuranceDetailMapper insuranceDetailMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ReminderMessageMapper reminderMessageMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationBuilder notificationBuilder;

    public void checkForExpiry() {
        LOGGER.info("Checking expiring policies...");
        List<InsuranceDetail> insuranceDetails = insuranceDetailMapper.findAll();
        LOGGER.info("Number of policies found, {}", insuranceDetails.size());

        LOGGER.info("Creating records in Email Out Table.");
        insuranceDetails.forEach(insuranceDetail -> {
            Customer customer = customerMapper.findCustomerById(insuranceDetail.getCustomerId());
            EmailOut emailOut = notificationBuilder.buildEmailMessage(customer, insuranceDetail);

            if (CollectionUtils.isEmpty(emailOutMapper.findExisting(emailOut))) {
                emailOutMapper.insert(emailOut);
                reminderMessageMapper.insert(notificationBuilder.buildSms(customer, insuranceDetail));
            } else {
                LOGGER.info("Skipping as record exist in Email Out Table. [{}]", emailOut);
            }
        });
        LOGGER.info("Record creation completed on Email Out Table.");
    }

//    @Scheduled(fixedRate = 10000)
    public void runNotifications() {
        LOGGER.info("Running notification process.");
        List<EmailOut> emailOuts = emailOutMapper.findAllPending();
        LOGGER.info("Received emailOut list, size = {}", emailOuts.size());
        List<CompletableFuture<EmailOut>> futureList = new ArrayList<>();
        emailOuts.forEach(emailOut -> {
            if (STATUS_PENDING.equals(emailOut.getStatus().trim())) {
                LOGGER.info("Processing notification for emailOut, [{}]", emailOut);
                futureList.add(CompletableFuture.supplyAsync(() -> notificationService.sendEmail(emailOut)));
            } else {
                LOGGER.info("Skipping emailOut, [{}]", emailOut);
            }
        });
        List<EmailOut> completedList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        completedList.forEach(emailOut -> {
            LOGGER.info("Update status for emailId - {}", emailOut.getToId());
            emailOut.setStatus(STATUS_SUCCESS);
            emailOutMapper.update(emailOut);
        });
        LOGGER.info("Process completed.");
    }
}
