package com.infosat.notification.server.mybatis.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author pradeepcg
 * @created 11/11/2020 - 06:45
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EmailOutMapperTest {

    @Autowired
    private EmailOutMapper emailOutMapper;

    @Test
    public void testFindAll() {
        Assert.assertNotNull(emailOutMapper);
        Assert.assertNotNull(emailOutMapper.findAll());

        emailOutMapper.findAll().forEach(emailOut -> {
            System.out.println(emailOut.getToId());
        });
    }
}
