package com.infosat.notification.server.mybatis.mapper;

import com.infosat.notification.server.mybatis.model.InsuranceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author pradeepcg
 * @created 14/11/2020 - 19:08
 */

@Mapper
public interface InsuranceDetailMapper {

    @Select("Select * From INSURANCEDETAILS Where cast(EXPIRYDATE as date) = cast(GETDATE()+10 as date)")
    @Results({
            @Result(property = "insuranceId", column = "INSURANCEID"),
            @Result(property = "customerId", column = "CUSTOMERID"),
            @Result(property = "insuranceType", column = "INSURANCETYPE"),
            @Result(property = "policyNumber", column = "POLICYNO"),
            @Result(property = "expiryDate", column = "EXPIRYDATE"),
            @Result(property = "createdDate", column = "CREATEDDATE"),
            @Result(property = "createdBy", column = "CREATEDBY"),
            @Result(property = "updatedDate", column = "UPDATEDDATE"),
            @Result(property = "updatedBy", column = "UPDATEDBY"),
            @Result(property = "deleted", column = "DELETED"),
    })
    public List<InsuranceDetail> findAll();
}
