package com.infosat.notification.server.component;

import com.infosat.notification.server.mybatis.mapper.CustomerMapper;
import com.infosat.notification.server.mybatis.model.Customer;
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
    private CustomerMapper customerMapper;

    @Autowired
    private NotificationService notificationService;

//    @Scheduled(fixedRate = 10000)
    public void runNotifications() {
        LOGGER.info("Starting notification service.");
        List<Customer> customers = customerMapper.findAll();
        LOGGER.info("Received customer list, size = {}", customers.size());
        List<CompletableFuture<Customer>> futureList = new ArrayList<>();
        customers.forEach(customer -> {
            LOGGER.info("Processing notification for customer, [{}]", customer);
            futureList.add(CompletableFuture.supplyAsync(() -> notificationService.sendEmail(customer)));
        });
        List<Customer> completedList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        completedList.forEach(customer -> {
            LOGGER.info("Update status for emailId - {}", customer.getEmail());
            customer.setNotificationStatus("COMPLETED");
            customerMapper.update(customer);
        });
        LOGGER.info("Process completed.");
    }
}
