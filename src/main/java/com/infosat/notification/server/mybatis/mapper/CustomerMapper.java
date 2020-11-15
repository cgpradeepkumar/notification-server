package com.infosat.notification.server.mybatis.mapper;

import com.infosat.notification.server.mybatis.model.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author pradeepcg
 * @created 14/11/2020 - 18:51
 */

@Mapper
public interface CustomerMapper {

    @Select("select * from CUSTOMERS Where CUSTOMERID=#{customerId}")
    @Results({@Result(property = "customerId", column = "CUSTOMERID"),
            @Result(property = "firstName", column = "FIRSTNAME"),
            @Result(property = "lastName", column = "LASTNAME"),
            @Result(property = "emailId", column = "EMAILID"),
            @Result(property = "primaryPhone", column = "PRIMARYPHONE"),
            @Result(property = "secondaryPhone", column = "SECONDARYPHONE"),
            @Result(property = "company", column = "COMPANY"),
            @Result(property = "deleted", column = "DELETED"),
            @Result(property = "createdBy", column = "CREATEDBY"),
            @Result(property = "createdDate", column = "CREATEDDATE"),
            @Result(property = "updatedBy", column = "UPDATEDBY"),
            @Result(property = "updatedDate", column = "UPDATEDDATE"),
    })
    public Customer findCustomerById(String customerId);
}
