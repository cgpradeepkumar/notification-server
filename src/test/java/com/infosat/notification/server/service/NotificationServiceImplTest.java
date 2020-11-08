package com.infosat.notification.server.service;

import com.infosat.notification.server.mybatis.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 13:25
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class NotificationServiceImplTest {

    @Autowired
    private NotificationServiceImpl notificationService;

    @Test
    public void testSendEmail() {
        Assert.assertNotNull(notificationService);
        Customer customer = new Customer();
        customer.setCustomerName("Pradeepkumar");
        customer.setEmail("cgpradeepkumar@gmail.com");
        customer.setId(1);
        customer.setNextService(new Date());
        customer.setNotificationStatus("PENDING");
        notificationService.sendEmail(customer);
    }
}
