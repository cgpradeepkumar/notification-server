package com.infosat.notification.server.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 12:22
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class GmailServiceImplTest {

    @Autowired
    GmailServiceImpl gmailService;

    @Test
    public void testSendMessage() {
        Assert.assertNotNull(gmailService);
        String to = "cgpradeepkumar@gmail.com";
        String subject = "test mail";
        String body = "This is sample body of email!";
        gmailService.sendMessage(to, subject, body);
    }

    @Test
    public void testSendMimeMessage() {
        Assert.assertNotNull(gmailService);
        String to = "cgpradeepkumar@gmail.com";
        String subject = "test mime mail";
        String body = "This is sample html body of email!";
        gmailService.sendMessage(to, subject, body);
    }
}
