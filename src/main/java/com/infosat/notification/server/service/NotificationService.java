package com.infosat.notification.server.service;

import com.infosat.notification.server.mybatis.model.EmailOut;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 13:10
 */
public interface NotificationService {

    public EmailOut sendEmail(EmailOut emailOut);

    public void sendSms();
}
