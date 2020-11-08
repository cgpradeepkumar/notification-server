package com.infosat.notification.server.mybatis.mapper;

import com.infosat.notification.server.mybatis.model.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author pradeepcg
 * @created 07/11/2020 - 16:20
 */

@Mapper
public interface CustomerMapper {

    @Select("select * from customers")
    @Results({@Result(property = "customerName", column = "customer_name"),
            @Result(property = "nextService", column = "next_service"),
            @Result(property = "notificationStatus", column = "notification_status")})
    public List<Customer> findAll();

    @Update("update customers set notification_status=#{notificationStatus} where id=#{id}")
    public void update(Customer customer);
}
