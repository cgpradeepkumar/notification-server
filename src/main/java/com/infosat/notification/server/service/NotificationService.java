package com.infosat.notification.server.service;

import com.infosat.notification.server.mybatis.model.Customer;

import java.util.Map;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 13:10
 */
public interface NotificationService {

    public Customer sendEmail(Customer customer);

    public void sendSms();
}
