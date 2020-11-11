package com.infosat.notification.server.component;

import com.infosat.notification.server.mybatis.mapper.EmailOutMapper;
import com.infosat.notification.server.mybatis.model.EmailOut;
import com.infosat.notification.server.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private EmailOutMapper emailOutMapper;

    @Autowired
    private NotificationService notificationService;

//    @Scheduled(fixedRate = 10000)
    public void runNotifications() {
        LOGGER.info("Starting notification service.");
        List<EmailOut> emailOuts = emailOutMapper.findAll();
        LOGGER.info("Received emailOut list, size = {}", emailOuts.size());
        List<CompletableFuture<EmailOut>> futureList = new ArrayList<>();
        emailOuts.forEach(emailOut -> {
            if (emailOut.getStatus().equals("P")) {
                LOGGER.info("Processing notification for emailOut, [{}]", emailOut);
                futureList.add(CompletableFuture.supplyAsync(() -> notificationService.sendEmail(emailOut)));
            } else {
                LOGGER.info("Skipping emailOut, [{}]", emailOut);
            }
        });
        List<EmailOut> completedList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        completedList.forEach(emailOut -> {
            LOGGER.info("Update status for emailId - {}", emailOut.getToId());
            emailOut.setStatus("S");
            emailOutMapper.update(emailOut);
        });
        LOGGER.info("Process completed.");
    }
}
